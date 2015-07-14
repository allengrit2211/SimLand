package com.simland.core.module.order.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

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

	/****
	 * 商品列表 key店铺ID val购物车明细
	 */
	private ConcurrentMap<Shop, Vector<CartItem>> cartItems;

	public ConcurrentMap<Shop, Vector<CartItem>> getCartItems() {

		if (cartItems == null)
			this.cartItems = new ConcurrentHashMap<Shop, Vector<CartItem>>();

		return cartItems;
	}

	public void setCartItems(ConcurrentMap<Shop, Vector<CartItem>> cartItems) {
		this.cartItems = cartItems;
	}

	private Map<String, Shop> skuIndex = new HashMap<String, Shop>();// 购物车商品sky索引

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

		if (cart == null)
			cart = new Cart();

		ConcurrentMap<Shop, Vector<CartItem>> cartItems = cart.getCartItems();

		for (Entry<Shop, Vector<CartItem>> e : cartItems.entrySet()) {
			Vector<CartItem> ci = e.getValue();
			for (int i = 0; ci != null && i < ci.size(); i++) {
				cart.skuIndex.put(ci.get(i).getSku(), e.getKey());
			}
		}

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
			cartItem.setSku(Commodity.getCommoditySku(c));

			if (cartItems.get(shop) != null) {
				cartItems.get(shop).add(cartItem);
			} else {
				Vector<CartItem> cl = new Vector<CartItem>();
				cl.add(cartItem);
				cartItems.put(shop, cl);
			}
		}

		return cart;
	}
}
