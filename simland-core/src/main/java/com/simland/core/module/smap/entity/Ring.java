package com.simland.core.module.smap.entity;

import java.util.List;

public class Ring implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;
	private java.lang.Integer cityid;
	private java.lang.Integer provinceid;
	private java.lang.String name;
	private java.lang.Integer status;
	private java.util.Date createtime;
	private java.lang.Integer sort;
	private java.lang.String color;

	private List<RingDetails> ringDetailss;

	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}

	public java.lang.Integer getCityid() {
		return this.cityid;
	}

	public void setCityid(java.lang.Integer value) {
		this.cityid = value;
	}

	public java.lang.Integer getProvinceid() {
		return this.provinceid;
	}

	public void setProvinceid(java.lang.Integer value) {
		this.provinceid = value;
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String value) {
		this.name = value;
	}

	public java.lang.Integer getStatus() {
		return this.status;
	}

	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}

	public java.util.Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}

	public java.lang.Integer getSort() {
		return this.sort;
	}

	public void setSort(java.lang.Integer value) {
		this.sort = value;
	}

	public java.lang.String getColor() {
		return this.color;
	}

	public void setColor(java.lang.String value) {
		this.color = value;
	}

	public List<RingDetails> getRingDetailss() {
		return ringDetailss;
	}

	public void setRingDetailss(List<RingDetails> ringDetailss) {
		this.ringDetailss = ringDetailss;
	}

}
