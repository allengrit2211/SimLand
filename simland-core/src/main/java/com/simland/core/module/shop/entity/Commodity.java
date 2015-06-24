package com.simland.core.module.shop.entity;

import java.util.*;
import java.math.BigDecimal;

public class Commodity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	private java.lang.Integer id;
	private java.lang.Integer sid;
	private java.lang.String name;
	private java.lang.String img;
	private java.lang.Integer type;
	private java.lang.Integer collectNum;
	private java.lang.Double marketPrice;
	private java.lang.Double realPrice;
	private java.util.Date createTime;
	private java.lang.String creater;
	private java.lang.Integer isNew;
	private java.lang.Integer isSpecial;
	
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
	public java.lang.String getName(){
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	public java.lang.String getImg(){
		return this.img;
	}
	
	public void setImg(java.lang.String value) {
		this.img = value;
	}
	public java.lang.Integer getType(){
		return this.type;
	}
	
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	public java.lang.Integer getCollectNum(){
		return this.collectNum;
	}
	
	public void setCollectNum(java.lang.Integer value) {
		this.collectNum = value;
	}
	public java.lang.Double getMarketPrice(){
		return this.marketPrice;
	}
	
	public void setMarketPrice(java.lang.Double value) {
		this.marketPrice = value;
	}
	public java.lang.Double getRealPrice(){
		return this.realPrice;
	}
	
	public void setRealPrice(java.lang.Double value) {
		this.realPrice = value;
	}
	public java.util.Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	public java.lang.String getCreater(){
		return this.creater;
	}
	
	public void setCreater(java.lang.String value) {
		this.creater = value;
	}
	public java.lang.Integer getIsNew(){
		return this.isNew;
	}
	
	public void setIsNew(java.lang.Integer value) {
		this.isNew = value;
	}
	public java.lang.Integer getIsSpecial(){
		return this.isSpecial;
	}
	
	public void setIsSpecial(java.lang.Integer value) {
		this.isSpecial = value;
	}
	


}
