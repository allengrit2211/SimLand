package com.simland.core.module.shop.entity;

public class CommodityInventory implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer cid;
	private java.lang.Integer attr1;
	private java.lang.Integer attr2;
	private java.lang.Integer nums;
	private java.lang.Double price;
	private java.lang.String image;// 商品图片
	private java.lang.String productCode;// 商品编码
	private java.lang.Integer sid;
	private java.lang.String cpvalue1;
	private java.lang.String cpvalue2;

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

	public java.lang.Integer getSid() {
		return sid;
	}

	public void setSid(java.lang.Integer sid) {
		this.sid = sid;
	}

	public java.lang.String getCpvalue1() {
		return cpvalue1;
	}

	public void setCpvalue1(java.lang.String cpvalue1) {
		this.cpvalue1 = cpvalue1;
	}

	public java.lang.String getCpvalue2() {
		return cpvalue2;
	}

	public void setCpvalue2(java.lang.String cpvalue2) {
		this.cpvalue2 = cpvalue2;
	}

	public java.lang.Integer getCid() {
		return cid;
	}

	public void setCid(java.lang.Integer cid) {
		this.cid = cid;
	}

	public java.lang.Double getPrice() {
		return price;
	}

	public void setPrice(java.lang.Double price) {
		this.price = price;
	}

	public java.lang.String getImage() {
		return image;
	}

	public void setImage(java.lang.String image) {
		this.image = image;
	}

	public java.lang.String getProductCode() {
		return productCode;
	}

	public void setProductCode(java.lang.String productCode) {
		this.productCode = productCode;
	}

}
