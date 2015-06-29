package com.simland.core.module.user.entity;

import java.util.List;

import com.simland.core.module.shop.entity.Commodity;

public class CollectShop implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;
	private java.lang.Integer uid;
	private java.lang.Integer sid;
	
	private java.util.Date createTime;

	private int score;
	private String cname;
	private java.lang.String recomm;
	
	private List<Commodity> rclist;//推荐商品列表

	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}

	public java.lang.Integer getUid() {
		return this.uid;
	}

	public void setUid(java.lang.Integer value) {
		this.uid = value;
	}

	public java.lang.Integer getSid() {
		return this.sid;
	}

	public void setSid(java.lang.Integer value) {
		this.sid = value;
	}

	public java.lang.String getRecomm() {
		return this.recomm;
	}

	public void setRecomm(java.lang.String value) {
		this.recomm = value;
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public List<Commodity> getRclist() {
		return rclist;
	}

	public void setRclist(List<Commodity> rclist) {
		this.rclist = rclist;
	}
	
	

}
