package com.simland.core.module.purview.entity;

import java.util.Map;

import com.simland.core.module.order.entity.Cart;
import com.simland.core.module.shop.entity.Shop;

public class ShopUser implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;
	private java.lang.String userName;
	private java.lang.String passWord;
	private java.lang.String passWord1;
	private java.lang.Integer rid;
	private java.lang.Integer type;
	private java.util.Date createTime;
	private java.lang.Integer sid;

	private Shop shop;// 店铺

	private Map<String, String> powers;// 用户所有权限

	private Cart cart;

	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}

	public java.lang.String getUserName() {
		return this.userName;
	}

	public void setUserName(java.lang.String value) {
		this.userName = value;
	}

	public java.lang.String getPassWord() {
		return this.passWord;
	}

	public void setPassWord(java.lang.String value) {
		this.passWord = value;
	}

	public java.lang.Integer getRid() {
		return this.rid;
	}

	public void setRid(java.lang.Integer value) {
		this.rid = value;
	}

	public java.lang.Integer getType() {
		return this.type;
	}

	public void setType(java.lang.Integer value) {
		this.type = value;
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}

	public java.lang.String getPassWord1() {
		return passWord1;
	}

	public void setPassWord1(java.lang.String passWord1) {
		this.passWord1 = passWord1;
	}

	public Map<String, String> getPowers() {
		return powers;
	}

	public void setPowers(Map<String, String> powers) {
		this.powers = powers;
	}

	public java.lang.Integer getSid() {
		return sid;
	}

	public void setSid(java.lang.Integer sid) {
		this.sid = sid;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

}
