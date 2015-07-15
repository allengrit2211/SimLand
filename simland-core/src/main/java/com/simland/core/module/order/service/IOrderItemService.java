package com.simland.core.module.order.service;

import java.util.List;
import java.util.Map;

import com.simland.core.module.order.entity.OrderItem;

@SuppressWarnings("unchecked")
public interface IOrderItemService {
	
	public Integer insertOrderItem(OrderItem orderItem);

	public Integer updateOrderItem(OrderItem orderItem);

	public Integer deleteOrderItem(Integer id);
	
	public OrderItem getOrderItem(Map param);
	
	public List<OrderItem> getOrderItemList(Map param);

	public Integer getOrderItemCount(Map param);
	
	public List<OrderItem> getSplitOrderItemList(Map param);
	
}
