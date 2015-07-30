package com.simland.core.module.smap.entity;

import java.util.*;
import java.math.BigDecimal;

public class RingDetails implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	private java.lang.Integer id;
	private java.lang.Integer rid;
	private java.lang.String point;
	
	public java.lang.Integer getId(){
		return this.id;
	}
	
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	public java.lang.Integer getRid(){
		return this.rid;
	}
	
	public void setRid(java.lang.Integer value) {
		this.rid = value;
	}
	public java.lang.String getPoint(){
		return this.point;
	}
	
	public void setPoint(java.lang.String value) {
		this.point = value;
	}
	


}
