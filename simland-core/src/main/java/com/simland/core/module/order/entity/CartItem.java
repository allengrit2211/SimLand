package com.simland.core.module.order.entity;

import com.simland.core.module.shop.entity.Commodity;

public class CartItem {

	private Commodity c;// 商品
	private Integer buyNum;// 购买数量
	private String sky;// 商品sku

	public Commodity getC() {
		return c;
	}

	public void setC(Commodity c) {
		this.c = c;
	}

	public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}

	public String getSky() {
		return sky;
	}

	public void setSky(String sky) {
		this.sky = sky;
	}

}
