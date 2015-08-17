package com.simland.core.module.shop.entity;

import java.sql.Timestamp;

public class Inventory implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;
	private java.lang.Integer cid;
	private java.lang.Integer attr1;
	private java.lang.Integer attr2;
	private java.lang.Integer nums;
	private java.lang.Double price;
	private java.lang.String image;
	private java.lang.String productCode;
	private java.lang.Integer status;
	private java.lang.Integer type;// 状态 0默认状态 1上架 2下架
	private java.lang.Integer sid;

	public static final Integer status_0 = 0;// 默认状态
	public static final Integer status_1 = 1;// 上架
	public static final Integer status_2 = 2;// 下架

	private Timestamp issueTime;// 发布时间
	private java.lang.String cause;// 下架原因

	private Commodity commodity;

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.Integer getCid() {
		return cid;
	}

	public void setCid(java.lang.Integer cid) {
		this.cid = cid;
	}

	public java.lang.Integer getAttr1() {
		return attr1;
	}

	public void setAttr1(java.lang.Integer attr1) {
		this.attr1 = attr1;
	}

	public java.lang.Integer getAttr2() {
		return attr2;
	}

	public void setAttr2(java.lang.Integer attr2) {
		this.attr2 = attr2;
	}

	public java.lang.Integer getNums() {
		return nums;
	}

	public void setNums(java.lang.Integer nums) {
		this.nums = nums;
	}

	public java.lang.Double getPrice() {
		return price;
	}

	public void setPrice(java.lang.Double price) {
		this.price = price;
	}

	public java.lang.Integer getStatus() {
		return status;
	}

	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}

	public java.lang.Integer getType() {
		return type;
	}

	public void setType(java.lang.Integer type) {
		this.type = type;
	}

	public java.lang.Integer getSid() {
		return sid;
	}

	public void setSid(java.lang.Integer sid) {
		this.sid = sid;
	}

	public java.lang.String getProductCode() {
		return productCode;
	}

	public void setProductCode(java.lang.String productCode) {
		this.productCode = productCode;
	}

	public java.lang.String getImage() {
		return image;
	}

	public void setImage(java.lang.String image) {
		this.image = image;
	}

	public Timestamp getIssueTime() {
		return issueTime;
	}

	public void setIssueTime(Timestamp issueTime) {
		this.issueTime = issueTime;
	}

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public java.lang.String getCause() {
		return cause;
	}

	public void setCause(java.lang.String cause) {
		this.cause = cause;
	}

}
