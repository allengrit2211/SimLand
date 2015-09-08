package com.simland.core.module.order.entity;

import java.util.Vector;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Component;

import com.simland.core.module.shop.entity.Shop;

/***
 * 结算信息
 * 
 * @author Gavin
 *
 */
@Component
public class Settlement {

	private double totalPrice;// 总价格
	private int quantity;// 商品总数量

	public static Settlement calculate(ConcurrentMap<Shop, Vector<CartItem>> cartItems) {
		if (cartItems == null)
			return null;

		Settlement settlement = new Settlement();
		int totalQuantity = 0;
		double totalPrice = 0;
		for (Entry<Shop, Vector<CartItem>> e : cartItems.entrySet()) {

			int quantity = 0;
			double price = 0;

			Vector<CartItem> ci = e.getValue();
			for (int i = 0; ci != null && i < ci.size(); i++) {
				CartItem c = ci.get(i);
				quantity += c.getBuyNum();
				price += (c.getBuyNum() * c.getPrice());
			}

			Settlement slt = new Settlement();
			slt.setQuantity(quantity);
			slt.setTotalPrice(price);
			e.getKey().setSettlement(slt);

			totalQuantity += quantity;
			totalPrice += price;
		}
		settlement.setQuantity(totalQuantity);
		settlement.setTotalPrice(totalPrice);
		return settlement;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
