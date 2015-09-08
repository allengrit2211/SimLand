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
	NEW(1, "待付款"),

	/*****
	 * 作废
	 */
	INVALID(2, "作废"),

	/*****
	 * 确认
	 */
	CONFIRM(3, "已确认"),

	/****
	 * 已经付款
	 */
	PAY_END(4, "已付款"),

	/*****
	 * 确认收货
	 */
	RECEIVING(5, "确认收货");

	OrderStatus(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	private Integer id;
	private String name;

	public Integer getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

}
