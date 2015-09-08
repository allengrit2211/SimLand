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
	 * 新建订单，待付款
	 */
	WAIT_PAY(1, "待付款"),

	/*****
	 * 新建订单，取消订单
	 */
	CANCEL(2, "已取消"),

	/****
	 * 已付款，待发货
	 */
	WAIT_SEND(3, "待发货"),

	/*****
	 * 已发货，待确认收货
	 */
	WAIT_RECEIVING(4, "待收货"),

	/***
	 * 已确认收货，待评论
	 */
	WAIT_COMMENT(5, "待评论"),

	/***
	 * 评论结束，订单完成
	 */
	COMPLETE(6, "完成");

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
