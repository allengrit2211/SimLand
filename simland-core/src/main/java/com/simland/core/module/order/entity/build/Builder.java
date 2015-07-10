package com.simland.core.module.order.entity.build;

import com.simland.core.module.order.entity.Order;

/***
 * 订单创建者类接口 ClassName: OrderBuilder
 * 
 * @Description: TODO
 * @author Gavin
 * @date 2015年7月10日
 */
public interface Builder {

	// 用户信息
	void buildUserInfo();

	// 商品信息
	void buildCommodity();

	Order getResult();
}
