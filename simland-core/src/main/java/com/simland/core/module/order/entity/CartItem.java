package com.simland.core.module.order.entity;

import com.simland.core.module.shop.entity.Commodity;

public class CartItem {

	private Commodity c;// 商品
	private Integer buyNum;// 购买数量
	private String sku;// 商品sku
	private Double price;// 商品价格

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

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
