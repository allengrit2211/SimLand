package com.simland.core.module.order.service;

import java.util.List;
import java.util.Map;

import com.simland.core.module.order.entity.Order;
import com.simland.core.module.order.entity.OrderItem;

public interface IOrderService {

	public Integer insertOrder(Order order);

	public Integer insertOrder(List<Order> orders);

	/***
	 * 保存订单
	 * 
	 * @param order
	 * @param orderItem
	 * @return
	 */
	public Integer insertOrder(Order order, List<OrderItem> orderItems);

	public Integer updateOrder(Order order);

	public Integer deleteOrder(Integer id);

	public Order getOrder(Map<String, Object> param);

	public Order getOrder(Integer id);

	public List<Order> getOrderList(Map<String, Object> param);

	public Integer getOrderCount(Map<String, Object> param);

	public List<Order> getSplitOrderList(Integer uid, Map<String, Object> param);

	public List<Order> getSplitOrderList(Map<String, Object> param);

	/***
	 * 取消订单
	 * 
	 * @param order
	 * @return
	 */
	public Integer cancelOrder(Order order);

}
