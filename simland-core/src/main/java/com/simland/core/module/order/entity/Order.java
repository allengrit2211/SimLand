package com.simland.core.module.order.entity;

import java.util.List;

import com.simland.core.module.shop.entity.Commodity;
import com.simland.core.module.user.entity.User;

/**
 * 订单实体类 ClassName: Order
 * 
 * @Description: TODO
 * @author Gavin
 * @date 2015年7月9日
 */
public class Order implements IOrder, java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private List<Commodity> commodityList;// 商品列表
	private User user;// 用户信息

	private java.lang.Integer id;
	private java.lang.Integer uid;
	private java.lang.Integer sid;
	private java.lang.Double total;
	private java.lang.Integer quantity;
	private java.util.Date createTime;
	private java.lang.Integer uaid;
	private java.lang.String receiverName;
	private java.lang.String receiverPhone;
	private java.lang.String receiverProvince;
	private java.lang.String receiverCity;
	private java.lang.String receiverDistrict;
	private java.lang.String receiverAdress;
	private java.lang.String receiverZipCode;
	private java.lang.Integer orderStatus;
	private java.lang.Integer logisticsStauts;
	private java.lang.Integer isDel;
	private java.lang.String remark;

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

	public java.lang.Double getTotal() {
		return this.total;
	}

	public void setTotal(java.lang.Double value) {
		this.total = value;
	}

	public java.lang.Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(java.lang.Integer value) {
		this.quantity = value;
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}

	public java.lang.Integer getUaid() {
		return this.uaid;
	}

	public void setUaid(java.lang.Integer value) {
		this.uaid = value;
	}

	public java.lang.String getReceiverName() {
		return this.receiverName;
	}

	public void setReceiverName(java.lang.String value) {
		this.receiverName = value;
	}

	public java.lang.String getReceiverPhone() {
		return this.receiverPhone;
	}

	public void setReceiverPhone(java.lang.String value) {
		this.receiverPhone = value;
	}

	public java.lang.String getReceiverProvince() {
		return this.receiverProvince;
	}

	public void setReceiverProvince(java.lang.String value) {
		this.receiverProvince = value;
	}

	public java.lang.String getReceiverCity() {
		return this.receiverCity;
	}

	public void setReceiverCity(java.lang.String value) {
		this.receiverCity = value;
	}

	public java.lang.String getReceiverDistrict() {
		return this.receiverDistrict;
	}

	public void setReceiverDistrict(java.lang.String value) {
		this.receiverDistrict = value;
	}

	public java.lang.String getReceiverAdress() {
		return this.receiverAdress;
	}

	public void setReceiverAdress(java.lang.String value) {
		this.receiverAdress = value;
	}

	public java.lang.String getReceiverZipCode() {
		return this.receiverZipCode;
	}

	public void setReceiverZipCode(java.lang.String value) {
		this.receiverZipCode = value;
	}

	public java.lang.Integer getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(java.lang.Integer value) {
		this.orderStatus = value;
	}

	public java.lang.Integer getLogisticsStauts() {
		return this.logisticsStauts;
	}

	public void setLogisticsStauts(java.lang.Integer value) {
		this.logisticsStauts = value;
	}

	public java.lang.Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(java.lang.Integer value) {
		this.isDel = value;
	}

	public java.lang.String getRemark() {
		return this.remark;
	}

	public void setRemark(java.lang.String value) {
		this.remark = value;
	}

	public List<Commodity> getCommodityList() {
		return commodityList;
	}

	public void setCommodityList(List<Commodity> commodityList) {
		this.commodityList = commodityList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
