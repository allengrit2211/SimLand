package com.simland.core.module.order.service;

import com.simland.core.base.SysMessage;
import com.simland.core.module.order.entity.Cart;
import com.simland.core.module.order.entity.Order;
import com.simland.core.module.user.entity.Address;
import com.simland.core.module.user.entity.User;

/****
 * 订单状态 抽象类
 * 
 * @Description:
 * @author Gavin
 * @date 2015年7月9日
 */
public interface IOrderState {

	/****
	 * 创建新订单
	 * 
	 * @param user
	 * @param address
	 * @param cart
	 * @param remarks
	 *            备注
	 * @return
	 */
	int create(User user, Address address, Cart cart,SysMessage msg, String... remarks);

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

	/****
	 * 订单完成
	 * 
	 * @param order
	 */
	void complete(Order order);

}
