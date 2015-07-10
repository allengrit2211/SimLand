package com.simland.core.module.order.service;

import com.simland.core.module.order.entity.Order;

/****
 * 订单状态 抽象类
 * 
 * @Description:
 * @author Gavin
 * @date 2015年7月9日
 */
public interface IOrderState {

	/***
	 * 创建新订单
	 * 
	 * @param order
	 */
	void create(Order order);

	/***
	 * 确认订单
	 * 
	 * @param order
	 */
	void confirm(Order order);

	/***
	 * 修改订单
	 * 
	 * @param order
	 */
	void modify(Order order);

	/***
	 * 付款
	 * 
	 * @param order
	 */
	void pay(Order order);
}
