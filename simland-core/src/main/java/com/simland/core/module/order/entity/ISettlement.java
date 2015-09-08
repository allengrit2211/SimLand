package com.simland.core.module.order.entity;

import java.util.Vector;
import java.util.concurrent.ConcurrentMap;

import com.simland.core.module.shop.entity.Shop;

/***
 * 结算借口
 * 
 * @author Gavin
 *
 */
public interface ISettlement {

	/***
	 * 计算
	 * 
	 * @param cartItems
	 */
	public void calculate(ConcurrentMap<Shop, Vector<CartItem>> cartItems);

}
