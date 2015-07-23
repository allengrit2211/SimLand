package com.simland.core.module.order.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.simland.core.module.order.mapper.OrderMapper;
import com.simland.core.module.order.entity.Order;
import com.simland.core.module.order.entity.OrderItem;
import com.simland.core.module.order.service.IOrderService;

@Service("orderService")
@Transactional(rollbackFor = java.lang.Exception.class)
public class OrderServiceImpl implements IOrderService{

	@Autowired
	private OrderMapper orderMapper;
	
	public Integer insertOrder(Order order) {
		return orderMapper.insertOrder(order);
	}

	public Integer updateOrder(Order order) {
		return orderMapper.updateOrder(order);
	}
	
	public Integer deleteOrder(Integer id) {
		return orderMapper.deleteOrder(id);
	}
	
	public Order getOrder(Map param) {
		return (Order)orderMapper.getOrder(param);
	}
	
	public List<Order> getOrderList(Map param) {
		return orderMapper.getOrderList(param);
	}
	
	public Integer getOrderCount(Map param) {
		return (Integer)orderMapper.getOrderCount(param);
	}
	
	public List<Order> getSplitOrderList(Map param) {
		return orderMapper.getSplitOrderList(param);
	}

	@Override
	public Integer insertOrder(Order order, OrderItem orderItem) {
		return null;
	}
	
}
