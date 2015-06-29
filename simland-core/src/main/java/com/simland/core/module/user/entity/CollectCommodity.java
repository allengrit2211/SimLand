package com.simland.core.module.user.entity;

import com.simland.core.module.shop.entity.Commodity;

public class CollectCommodity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	private java.lang.Integer id;
	private java.lang.Integer uid;
	private java.lang.Integer cid;
	private java.util.Date createTime;
	private Commodity commodity;
	
	public java.lang.Integer getId(){
		return this.id;
	}
	
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	public java.lang.Integer getUid(){
		return this.uid;
	}
	
	public void setUid(java.lang.Integer value) {
		this.uid = value;
	}
	public java.lang.Integer getCid(){
		return this.cid;
	}
	
	public void setCid(java.lang.Integer value) {
		this.cid = value;
	}
	public java.util.Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}
	


}
