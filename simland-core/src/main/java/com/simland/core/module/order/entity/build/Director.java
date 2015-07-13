package com.simland.core.module.order.entity.build;


/***
 * 订单创建者 ClassName: OrderDirector
 * 
 * @Description: TODO
 * @author Gavin
 * @date 2015年7月10日
 */
public class Director {

	private Builder builder;

	public Director(Builder builder) {
		this.builder = builder;
	}

	public void construct() {
		builder.buildUserInfo();
		builder.buildCommodity();
	}

}
