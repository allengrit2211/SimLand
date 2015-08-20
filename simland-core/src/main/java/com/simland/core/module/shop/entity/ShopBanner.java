package com.simland.core.module.shop.entity;


public class ShopBanner implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	private java.lang.Integer id;
	private java.lang.Integer sid;
	private java.lang.String picUrl;
	private java.lang.String linkUrl;
	
	public java.lang.Integer getId(){
		return this.id;
	}
	
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	public java.lang.Integer getSid(){
		return this.sid;
	}
	
	public void setSid(java.lang.Integer value) {
		this.sid = value;
	}
	public java.lang.String getPicUrl(){
		return this.picUrl;
	}
	
	public void setPicUrl(java.lang.String value) {
		this.picUrl = value;
	}
	public java.lang.String getLinkUrl(){
		return this.linkUrl;
	}
	
	public void setLinkUrl(java.lang.String value) {
		this.linkUrl = value;
	}
	


}
