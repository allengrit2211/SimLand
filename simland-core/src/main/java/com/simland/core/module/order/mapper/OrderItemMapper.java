package com.simland.core.module.order.mapper;

import java.util.List;
import java.util.Map;

import com.simland.core.module.order.entity.OrderItem;

public interface OrderItemMapper{
	
	public Integer insertOrderItem(OrderItem orderItem);  	
	
	public Integer insertBatchOrderItem(List<OrderItem> orderItems);
	
	public Integer updateOrderItem(OrderItem orderItem);
	
	public Integer deleteOrderItem(Integer id);

	public OrderItem getOrderItem(Map param);
	
	public List getOrderItemList(Map param);

	public Integer getOrderItemCount(Map param);
	
	public List getSplitOrderItemList(Map param);

}
