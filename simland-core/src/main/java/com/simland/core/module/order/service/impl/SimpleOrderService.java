package com.simland.core.module.order.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.simland.core.module.order.entity.Order;
import com.simland.core.module.order.service.IOrderService;
import com.simland.core.module.order.service.IOrderState;

/***
 * 
 * ClassName: SimpleOrderService
 * 
 * @Description: 普通订单处理
 * @author Gavin
 * @date 2015年7月9日
 */
@Service("simpleOrderService")
public class SimpleOrderService implements IOrderState, IOrderService {

	@Override
	public void create(Order order) {

	}

	@Override
	public void confirm(Order order) {

	}

	@Override
	public void modify(Order order) {

	}

	@Override
	public void pay(Order order) {

	}

	@Override
	public Integer insertOrder(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateOrder(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteOrder(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order getOrder(Map param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getOrderList(Map param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getOrderCount(Map param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getSplitOrderList(Map param) {
		// TODO Auto-generated method stub
		return null;
	}

}
