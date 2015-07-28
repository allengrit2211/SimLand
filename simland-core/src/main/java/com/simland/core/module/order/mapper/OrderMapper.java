package com.simland.core.module.order.mapper;

import java.util.List;
import java.util.Map;

import com.simland.core.module.order.entity.Order;

public interface OrderMapper{
	
	public Integer insertOrder(Order order);  	
	
	public Integer updateOrder(Order order);
	
	public Integer deleteOrder(Integer id);

	public Order getOrder(Map param);
	
	public List getOrderList(Map param);

	public Integer getOrderCount(Map param);
	
	public List getSplitOrderList(Map param);

}
