package com.simland.core.module.order.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.simland.core.base.Utils;
import com.simland.core.module.shop.entity.Commodity;
import com.simland.core.module.shop.entity.Shop;

/***
 * 
 * ClassName: Cart
 * 
 * @Description: 购物车类
 * @author Gavin
 * @date 2015年7月10日
 */
public class Cart {

	public static final Log logger = LogFactory.getLog(Cart.class);

	/****
	 * 购物车结算信息
	 */
	private Settlement cartSettlement;

	/***
	 * 待结算信息
	 */
	private Settlement waitSettlementItems;

	/****
	 * 购物车名明细 <店铺ID,购物车明细>
	 */
	private ConcurrentMap<Shop, Vector<CartItem>> cartItems;

	/***
	 * 待结算商品明细
	 */
	private ConcurrentMap<Shop, Vector<CartItem>> settlementItems;

	/***
	 * 购物车商品sku索引<br>
	 * 购物车中商品sku与店铺 k,v
	 */
	private Map<String, Shop> skuIndex = new HashMap<String, Shop>();

	public ConcurrentMap<Shop, Vector<CartItem>> getCartItems() {

		if (cartItems == null)
			this.cartItems = new ConcurrentHashMap<Shop, Vector<CartItem>>();

		return cartItems;
	}

	public ConcurrentMap<Shop, Vector<CartItem>> getSettlementItems() {
		if (settlementItems == null)
			this.settlementItems = new ConcurrentHashMap<Shop, Vector<CartItem>>();

		return settlementItems;
	}

	public void setSettlementItems(ConcurrentMap<Shop, Vector<CartItem>> settlementItems) {
		this.settlementItems = settlementItems;
	}

	public Map<String, Shop> getSkuIndex() {
		return skuIndex;
	}

	public void setSkuIndex(Map<String, Shop> skuIndex) {
		this.skuIndex = skuIndex;
	}

	public void setCartItems(ConcurrentMap<Shop, Vector<CartItem>> cartItems) {
		this.cartItems = cartItems;
	}

	/***
	 * 添加购物车
	 * 
	 * @param cartList
	 *            用户购物车列表
	 * @param c
	 *            新增购物商品
	 * @param buyNum
	 *            购买数量
	 * @return
	 */
	public static Cart addCart(Cart cart, Shop shop, Commodity c, int buyNum) {

		if (shop == null || c == null || buyNum <= 0) {
			logger.error("cart=" + cart + " shop=" + shop + " Commodity=" + c + " buyNum=" + buyNum);
			return new Cart();
		}

		if (cart == null)
			cart = new Cart();

		ConcurrentMap<Shop, Vector<CartItem>> cartItems = cart.getCartItems();

		/****
		 * 添加购物车功能
		 */
		String sku = Commodity.getCommoditySku(c);
		if (cart.skuIndex.containsKey(sku)) {// 存在该商品
			Vector<CartItem> ciList = cartItems.get(cart.skuIndex.get(sku));
			for (int i = 0; ciList != null && i < ciList.size(); i++) {
				if (Commodity.getCommoditySku(c).equals(ciList.get(i).getSku())) {
					ciList.get(i).setBuyNum(ciList.get(i).getBuyNum() + buyNum);
				}
			}
		} else {// 不存在
			CartItem cartItem = new CartItem();
			cartItem.setC(c);
			cartItem.setBuyNum(buyNum);
			cartItem.setSku(sku);
			cartItem.setPrice(Utils.isObjectEmpty(Commodity.getCommodityPrice(c, sku)) ? c.getMarketPrice() : Commodity
					.getCommodityPrice(c, sku));

			if (cartItems.get(shop) != null) {
				cartItems.get(shop).add(cartItem);
			} else {
				Vector<CartItem> cl = new Vector<CartItem>();
				cl.add(cartItem);
				cartItems.put(shop, cl);
			}
		}

		/****
		 * 每次添加购物车时，所有商品以 <sku,shop> 形式添加到 skuIndex中
		 */
		for (Entry<Shop, Vector<CartItem>> e : cartItems.entrySet()) {
			Vector<CartItem> ci = e.getValue();
			for (int i = 0; ci != null && i < ci.size(); i++) {
				cart.skuIndex.put(ci.get(i).getSku(), e.getKey());
			}
		}

		// 结算信息计算
		cart.cartSettlement = Settlement.calculate(cart.getCartItems());

		return cart;
	}

	/**
	 * 删除元素
	 */
	public static Cart delCart(Cart cart, String... skus) {

		if (cart == null || skus == null || skus.length == 0)
			return cart;

		Set<String> removeSkus = new HashSet<String>();
		for (String sku : skus) {
			cart.skuIndex.remove(sku);// 从sku索引中移除
			removeSkus.add(sku);
		}

		Iterator<Shop> shops = cart.getCartItems().keySet().iterator();
		while (shops.hasNext()) {
			Shop shop = shops.next();

			Iterator<CartItem> cartItems = cart.getCartItems().get(shop).iterator();
			while (cartItems.hasNext()) {
				CartItem cartItem = cartItems.next();
				if (removeSkus.contains(cartItem.getSku())) {
					cartItems.remove();
				}
			}

			if (cart.getCartItems().get(shop) == null || cart.getCartItems().get(shop).size() == 0) {
				shops.remove();
			}
		}

		// 结算信息计算
		cart.cartSettlement = Settlement.calculate(cart.getCartItems());
		
		return cart;
	}

	/***
	 * 添加结算商品<br>
	 * 去除包含的sku
	 * 
	 * 
	 * @return
	 */
	public static Cart addSettlementItems(Cart cart, String... skus) {

		if (cart == null)
			return cart;

		boolean flagNull = false;// 直接购买时，默认全部结算
		if (skus == null || skus.length == 0)
			flagNull = true;

		Set<String> set = Utils.toSet(skus);

		cart.setSettlementItems(null);

		for (Entry<Shop, Vector<CartItem>> e : cart.getCartItems().entrySet()) {
			Vector<CartItem> cartItems = null;
			for (CartItem e1 : e.getValue()) {
				if (set.contains(e1.getSku()) || flagNull) {
					cartItems = cart.getSettlementItems().get(e.getKey());
					if (cartItems == null) {
						cartItems = new Vector<CartItem>();
						cartItems.add(e1);
						cart.getSettlementItems().put(e.getKey(), cartItems);
					} else {
						cartItems.add(e1);
					}
				}
			}
		}

		set = null;

		// 结算信息计算
		cart.waitSettlementItems = Settlement.calculate(cart.getSettlementItems());

		return cart;
	}

	public Settlement getCartSettlement() {
		return cartSettlement;
	}

	public void setCartSettlement(Settlement cartSettlement) {
		this.cartSettlement = cartSettlement;
	}

	public Settlement getWaitSettlementItems() {
		return waitSettlementItems;
	}

	public void setWaitSettlementItems(Settlement waitSettlementItems) {
		this.waitSettlementItems = waitSettlementItems;
	}
	
	
}
