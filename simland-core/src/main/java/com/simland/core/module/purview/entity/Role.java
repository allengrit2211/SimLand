package com.simland.core.module.purview.entity;

import java.util.List;

public class Role implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;
	private java.lang.String name;
	private java.util.Date createTime;
	private Integer sid;

	private List<Power> psowers;
	private String[] powerChk;

	public Role() {

	}

	public Role(Integer id, String name, String[] powerChk) {
		this.id = id;
		this.name = name;
		this.powerChk = powerChk;
	}

	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String value) {
		this.name = value;
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}

	public List<Power> getPsowers() {
		return psowers;
	}

	public void setPsowers(List<Power> psowers) {
		this.psowers = psowers;
	}

	public String[] getPowerChk() {
		return powerChk;
	}

	public void setPowerChk(String[] powerChk) {
		this.powerChk = powerChk;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}
	
	

}
