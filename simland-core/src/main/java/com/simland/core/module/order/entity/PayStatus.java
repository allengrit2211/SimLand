package com.simland.core.module.order.entity;

/****
 * 
 * ClassName: PayStatus
 * 
 * @Description: 订单支付状态
 * @author Gavin
 * @date 2015年7月28日
 */
public enum PayStatus {

	/***
	 * 待付款
	 */
	PAY_WAIT(0),
	/***
	 * 已付款
	 */
	PAY_END(1);

	PayStatus(Integer id) {
		this.id = id;
	}

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
