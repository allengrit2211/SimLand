package com.simland.core.module.shop.entity;


public class CategoryPropertiesVal implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;
	private java.lang.Integer cid;
	private java.lang.Integer cpid;
	private java.lang.String cpvalue;
	private java.lang.Integer isDel;

	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}

	public java.lang.Integer getCid() {
		return this.cid;
	}

	public void setCid(java.lang.Integer value) {
		this.cid = value;
	}

	public java.lang.Integer getCpid() {
		return this.cpid;
	}

	public void setCpid(java.lang.Integer value) {
		this.cpid = value;
	}

	public java.lang.String getCpvalue() {
		return this.cpvalue;
	}

	public void setCpvalue(java.lang.String value) {
		this.cpvalue = value;
	}

	public java.lang.Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(java.lang.Integer isDel) {
		this.isDel = isDel;
	}

}
