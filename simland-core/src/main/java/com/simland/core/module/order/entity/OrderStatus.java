package com.simland.core.module.order.entity;

/***
 * 
 * ClassName: OrderStauts
 * 
 * @Description: 订单状态类
 * @author Gavin
 * @date 2015年7月27日
 */
public enum OrderStatus {

	/***
	 * 新建
	 */
	NEW(1),

	/*****
	 * 确认
	 */
	CONFIRM(2),

	/****
	 * 已经付款
	 */
	PAY_END(3),

	/*****
	 * 确认收货
	 */
	RECEIVING(4),

	/*****
	 * 作废
	 */
	INVALID(5);

	OrderStatus(Integer id) {
		this.id = id;
	}

	private Integer id;

	public Integer getId() {
		return this.id;
	}

}
