package com.simland.core.module.chat.entity;

import java.util.*;
import java.math.BigDecimal;

public class Message implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	private java.lang.Integer id;
	private java.lang.Integer sid;
	private java.lang.Integer uid;
	private java.lang.String message;
	private java.util.Date sendTime;
	private java.lang.Integer sendType;
	
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
	public java.lang.Integer getUid(){
		return this.uid;
	}
	
	public void setUid(java.lang.Integer value) {
		this.uid = value;
	}
	public java.lang.String getMessage(){
		return this.message;
	}
	
	public void setMessage(java.lang.String value) {
		this.message = value;
	}
	public java.util.Date getSendTime(){
		return this.sendTime;
	}
	
	public void setSendTime(java.util.Date value) {
		this.sendTime = value;
	}
	public java.lang.Integer getSendType(){
		return this.sendType;
	}
	
	public void setSendType(java.lang.Integer value) {
		this.sendType = value;
	}
	


}
