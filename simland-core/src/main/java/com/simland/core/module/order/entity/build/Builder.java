package com.simland.core.module.order.entity.build;

import java.util.List;

import com.simland.core.module.order.entity.Order;

/***
 * 订单创建者类接口 ClassName: OrderBuilder
 * 
 * @Description: TODO
 * @author Gavin
 * @date 2015年7月10日
 */
public interface Builder {

	/***
	 * 设置订单用户信息
	 */
	void buildUserInfo();

	/****
	 * 设置订单商品信息
	 */
	void buildOrderItems();

	/***
	 * 设置订单活动信息
	 */
	void buildActivity();

	List<Order> getResult();
}
