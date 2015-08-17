package com.simland.core.module.order.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.simland.core.module.order.mapper.OrderItemMapper;
import com.simland.core.module.order.entity.OrderItem;
import com.simland.core.module.order.service.IOrderItemService;

@Service("orderItemService")
@Transactional(readOnly=true)
public class OrderItemServiceImpl implements IOrderItemService{

	@Autowired
	private OrderItemMapper orderItemMapper;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Integer insertOrderItem(OrderItem orderItem) {
		return orderItemMapper.insertOrderItem(orderItem);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Integer updateOrderItem(OrderItem orderItem) {
		return orderItemMapper.updateOrderItem(orderItem);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Integer deleteOrderItem(Integer id) {
		return orderItemMapper.deleteOrderItem(id);
	}
	
	public OrderItem getOrderItem(Map param) {
		return (OrderItem)orderItemMapper.getOrderItem(param);
	}
	
	public List<OrderItem> getOrderItemList(Map param) {
		return orderItemMapper.getOrderItemList(param);
	}
	
	public Integer getOrderItemCount(Map param) {
		return (Integer)orderItemMapper.getOrderItemCount(param);
	}
	
	public List<OrderItem> getSplitOrderItemList(Map param) {
		return orderItemMapper.getSplitOrderItemList(param);
	}
	
}
