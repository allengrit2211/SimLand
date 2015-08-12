package com.simland.core.module.purview.entity;

import java.util.*;
import java.math.BigDecimal;

public class RolePower implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;
	private java.lang.Integer rid;
	private java.lang.Integer pid;
	private java.util.Date createTime;

	public RolePower() {

	}

	public RolePower(Integer rid, Integer pid) {
		this.rid = rid;
		this.pid = pid;
	}

	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}

	public java.lang.Integer getRid() {
		return this.rid;
	}

	public void setRid(java.lang.Integer value) {
		this.rid = value;
	}

	public java.lang.Integer getPid() {
		return this.pid;
	}

	public void setPid(java.lang.Integer value) {
		this.pid = value;
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}

}
