package com.simland.core.module.purview.entity;

import java.util.*;
import java.math.BigDecimal;

public class Power implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;
	private java.lang.String name;
	private java.lang.String url;
	private java.lang.Integer pid;
	private java.util.Date createTime;

	private int isCheck;// 0未选 1 已选择

	private List<Power> subPowerList;

	public Power() {

	}

	public Power(Integer id, String name, String url, int pid) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.pid = pid;
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

	public java.lang.String getUrl() {
		return this.url;
	}

	public void setUrl(java.lang.String value) {
		this.url = value;
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

	public List<Power> getSubPowerList() {
		return subPowerList;
	}

	public void setSubPowerList(List<Power> subPowerList) {
		this.subPowerList = subPowerList;
	}

	public int getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(int isCheck) {
		this.isCheck = isCheck;
	}

}
