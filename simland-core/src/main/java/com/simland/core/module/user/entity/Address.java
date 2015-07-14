package com.simland.core.module.user.entity;


public class Address implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	private java.lang.Integer id;
	private java.lang.Integer uid;
	private java.lang.String receiverName;
	private java.lang.String receiverPhone;
	private java.lang.String receiverProvince;
	private java.lang.String receiverCity;
	private java.lang.String receiverDistrict;
	private java.lang.String receiverAdress;
	private java.lang.String receiverZipCode;
	private java.lang.Integer isDefault;
	
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
	public java.lang.String getReceiverName(){
		return this.receiverName;
	}
	
	public void setReceiverName(java.lang.String value) {
		this.receiverName = value;
	}
	public java.lang.String getReceiverPhone(){
		return this.receiverPhone;
	}
	
	public void setReceiverPhone(java.lang.String value) {
		this.receiverPhone = value;
	}
	public java.lang.String getReceiverProvince(){
		return this.receiverProvince;
	}
	
	public void setReceiverProvince(java.lang.String value) {
		this.receiverProvince = value;
	}
	public java.lang.String getReceiverCity(){
		return this.receiverCity;
	}
	
	public void setReceiverCity(java.lang.String value) {
		this.receiverCity = value;
	}
	public java.lang.String getReceiverDistrict(){
		return this.receiverDistrict;
	}
	
	public void setReceiverDistrict(java.lang.String value) {
		this.receiverDistrict = value;
	}
	public java.lang.String getReceiverAdress(){
		return this.receiverAdress;
	}
	
	public void setReceiverAdress(java.lang.String value) {
		this.receiverAdress = value;
	}
	public java.lang.String getReceiverZipCode(){
		return this.receiverZipCode;
	}
	
	public void setReceiverZipCode(java.lang.String value) {
		this.receiverZipCode = value;
	}
	public java.lang.Integer getIsDefault(){
		return this.isDefault;
	}
	
	public void setIsDefault(java.lang.Integer value) {
		this.isDefault = value;
	}
	


}
