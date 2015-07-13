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
public class Order implements IOrder {

	private List<Commodity> commodityList;// 商品列表
	private int buyNum;// 购买数量
	private Integer uid;// 用户id
	private User user;// 用户信息

	public List<Commodity> getCommodityList() {
		return commodityList;
	}

	public void setCommodityList(List<Commodity> commodityList) {
		this.commodityList = commodityList;
	}

	public int getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
