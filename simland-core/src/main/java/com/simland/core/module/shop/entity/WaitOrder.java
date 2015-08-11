package com.simland.core.module.shop.entity;

public class WaitOrder implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;
	private java.lang.Integer uid;
	private java.lang.String username;
	private java.lang.Integer sid;
	private java.util.Date createTime;
	private java.lang.String remark;
	private java.lang.Integer isDel;

	public static final Integer ISDEL_0 = 0;
	public static final Integer ISDEL_1 = 1;// 已经删除

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

	public java.lang.String getUsername() {
		return this.username;
	}

	public void setUsername(java.lang.String value) {
		this.username = value;
	}

	public java.lang.Integer getSid() {
		return this.sid;
	}

	public void setSid(java.lang.Integer value) {
		this.sid = value;
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}

	public java.lang.String getRemark() {
		return this.remark;
	}

	public void setRemark(java.lang.String value) {
		this.remark = value;
	}

	public java.lang.Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(java.lang.Integer isDel) {
		this.isDel = isDel;
	}

}
