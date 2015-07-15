package com.simland.core.module.order.service;

import java.util.List;
import java.util.Map;

import com.simland.core.module.order.entity.Order;

@SuppressWarnings("unchecked")
public interface IOrderService {
	
	public Integer insertOrder(Order order);

	public Integer updateOrder(Order order);

	public Integer deleteOrder(Integer id);
	
	public Order getOrder(Map param);
	
	public List<Order> getOrderList(Map param);

	public Integer getOrderCount(Map param);
	
	public List<Order> getSplitOrderList(Map param);
	
}
