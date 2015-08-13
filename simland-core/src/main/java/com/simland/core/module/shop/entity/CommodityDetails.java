package com.simland.core.module.shop.entity;


public class CommodityDetails implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	private java.lang.Integer id;
	private java.lang.Integer cid;
	private java.lang.String info;
	
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
	public java.lang.String getInfo(){
		return this.info;
	}
	
	public void setInfo(java.lang.String value) {
		this.info = value;
	}
	


}
