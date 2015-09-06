package com.simland.core.module.order.entity;

import java.util.*;
import java.math.BigDecimal;

public class OrderItem implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;
	private java.lang.Integer oid;
	private java.lang.Integer cid;
	private java.lang.String cname;
	private java.lang.Integer attr1Id;
	private java.lang.String attr1Val;
	private java.lang.Integer attr2Id;
	private java.lang.String attr2Val;
	private java.lang.Integer buyNum;
	private java.lang.Double cprice;
	private java.util.Date createTime;

	private java.lang.Integer iid;// 库存id

	private Order order;

	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}

	public java.lang.Integer getOid() {
		return this.oid;
	}

	public void setOid(java.lang.Integer value) {
		this.oid = value;
	}

	public java.lang.Integer getCid() {
		return this.cid;
	}

	public void setCid(java.lang.Integer value) {
		this.cid = value;
	}

	public java.lang.String getCname() {
		return this.cname;
	}

	public void setCname(java.lang.String value) {
		this.cname = value;
	}

	public java.lang.Integer getAttr1Id() {
		return this.attr1Id;
	}

	public void setAttr1Id(java.lang.Integer value) {
		this.attr1Id = value;
	}

	public java.lang.String getAttr1Val() {
		return this.attr1Val;
	}

	public void setAttr1Val(java.lang.String value) {
		this.attr1Val = value;
	}

	public java.lang.Integer getAttr2Id() {
		return this.attr2Id;
	}

	public void setAttr2Id(java.lang.Integer value) {
		this.attr2Id = value;
	}

	public java.lang.String getAttr2Val() {
		return this.attr2Val;
	}

	public void setAttr2Val(java.lang.String value) {
		this.attr2Val = value;
	}

	public java.lang.Integer getBuyNum() {
		return this.buyNum;
	}

	public void setBuyNum(java.lang.Integer value) {
		this.buyNum = value;
	}

	public java.lang.Double getCprice() {
		return this.cprice;
	}

	public void setCprice(java.lang.Double value) {
		this.cprice = value;
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public java.lang.Integer getIid() {
		return iid;
	}

	public void setIid(java.lang.Integer iid) {
		this.iid = iid;
	}

}
