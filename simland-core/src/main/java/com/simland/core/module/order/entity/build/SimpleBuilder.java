package com.simland.core.module.order.entity.build;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.Map.Entry;

import com.simland.core.module.order.entity.Cart;
import com.simland.core.module.order.entity.CartItem;
import com.simland.core.module.order.entity.Order;
import com.simland.core.module.order.entity.OrderItem;
import com.simland.core.module.shop.entity.Shop;
import com.simland.core.module.user.entity.User;

public class SimpleBuilder implements Builder {

	private User user;// 用户信息

	private Shop shop;// 购物车

	private Vector<CartItem> cartItem;

	private Order order = new Order();
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();

	public SimpleBuilder(User user, Shop shop, Vector<CartItem> cartItem) {
		this.user = user;
		this.shop = shop;
		this.cartItem = cartItem;
	}

	@Override
	public void buildUserInfo() {
		order.setUid(user.getId());
	}

	@Override
	public void buildOrderItems() {
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		for (CartItem ci : cartItem) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCid(ci.getC().getId());
			orderItem.setCprice(ci.getPrice());
			orderItems.add(orderItem);
		}

	}

	@Override
	public void buildActivity() {

	}

	@Override
	public List<Order> getResult() {

		Order order = new Order();
		return null;
	}

}
