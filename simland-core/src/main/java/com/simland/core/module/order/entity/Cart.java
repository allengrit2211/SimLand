package com.simland.core.module.order.entity;

import java.util.Collection;
import java.util.Map;
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
		return cartItems;
	}

	public void setCartItems(ConcurrentMap<Shop, Vector<CartItem>> cartItems) {
		this.cartItems = cartItems;
	}

	private Map<String, String> skuIndex;// 购物车商品sky索引

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

		if(cart==null) cart = new Cart();
		
		ConcurrentMap<Shop, Vector<CartItem>> cartItems = cart.getCartItems();
		if (cartItems == null || cartItems.size() == 0) {// 购物车为空时
			cartItems = new ConcurrentHashMap<Shop, Vector<CartItem>>();
			Vector<CartItem> cilist = new Vector<CartItem>();
			CartItem cartItem = new CartItem();
			cartItem.setC(c);
			cartItem.setSky(Commodity.getCommoditySku(c));
			cilist.add(cartItem);
			cartItems.put(shop, cilist);
			cart.setCartItems(cartItems);
		} else {// 购物车不为空 ，存在相同商品 数量增加
			Collection<Vector<CartItem>> allCartItem = cartItems.values();
			for (Vector<CartItem> vci : allCartItem) {
				for (CartItem cartItem : vci) {
					if (Commodity.getCommoditySku(c).equals(cartItem.getSky())) {// 存在相同商品，购买数量+buyNum
						cartItem.setBuyNum(cartItem.getBuyNum() + buyNum);
					} else {
						CartItem cartItem1 = new CartItem();
						cartItem1.setBuyNum(buyNum);
						cartItem1.setC(c);
						cartItem1.setSky(Commodity.getCommoditySku(c));
						vci.add(cartItem1);
					}
				}
			}
		}
		return cart;
	}
}
