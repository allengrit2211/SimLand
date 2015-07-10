package com.simland.core.module.shop.entity;


public class Inventory implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	private java.lang.Integer id;
	private java.lang.Integer cid;
	private java.lang.Integer attr1;
	private java.lang.Integer attr2;
	private java.lang.Integer nums;
	private java.lang.Integer status;
	private java.lang.Integer type;
	private java.lang.Integer sid;
	
	public java.lang.Integer getId(){
		return this.id;
	}
	
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	public java.lang.Integer getCid(){
		return this.cid;
	}
	
	public void setCid(java.lang.Integer value) {
		this.cid = value;
	}
	public java.lang.Integer getAttr1(){
		return this.attr1;
	}
	
	public void setAttr1(java.lang.Integer value) {
		this.attr1 = value;
	}
	public java.lang.Integer getAttr2(){
		return this.attr2;
	}
	
	public void setAttr2(java.lang.Integer value) {
		this.attr2 = value;
	}
	public java.lang.Integer getNums(){
		return this.nums;
	}
	
	public void setNums(java.lang.Integer value) {
		this.nums = value;
	}
	public java.lang.Integer getStatus(){
		return this.status;
	}
	
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	public java.lang.Integer getType(){
		return this.type;
	}
	
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	public java.lang.Integer getSid(){
		return this.sid;
	}
	
	public void setSid(java.lang.Integer value) {
		this.sid = value;
	}
	


}
