package com.simland.core.module.order.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simland.core.base.Utils;
import com.simland.core.module.order.entity.Order;
import com.simland.core.module.order.entity.OrderItem;
import com.simland.core.module.order.mapper.OrderItemMapper;
import com.simland.core.module.order.mapper.OrderMapper;
import com.simland.core.module.order.service.IOrderService;

@Service("orderService")
@Transactional(rollbackFor = java.lang.Exception.class)
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private OrderItemMapper orderItemMapper;

	public Integer insertOrder(Order order) {
		return orderMapper.insertOrder(order);
	}

	public Integer updateOrder(Order order) {
		return orderMapper.updateOrder(order);
	}

	public Integer deleteOrder(Integer id) {
		return orderMapper.deleteOrder(id);
	}

	public Order getOrder(Map<String, Object> param) {
		return (Order) orderMapper.getOrder(param);
	}

	public List<Order> getOrderList(Map<String, Object> param) {
		return orderMapper.getOrderList(param);
	}

	public Integer getOrderCount(Map<String, Object> param) {
		return (Integer) orderMapper.getOrderCount(param);
	}

	public List<Order> getSplitOrderList(Map<String, Object> param) {
		return orderMapper.getSplitOrderList(param);
	}

	@Override
	public Integer insertOrder(Order order, List<OrderItem> orderItems) {
		return null;
	}

	@Override
	public Integer insertOrder(List<Order> orders) {

		if (orders == null || orders.size() == 0)
			return -1;

		for (Order order : orders) {
			orderMapper.insertOrder(order);

			for (OrderItem orderItem : order.getOrderItems()) {
				orderItem.setOid(order.getId());
			}

			orderItemMapper.insertBatchOrderItem(order.getOrderItems());

		}

		return 1;
	}

	@Override
	public List<Order> getSplitOrderList(Integer uid, Map<String, Object> param) {

		if (Utils.isObjectEmpty(uid))
			return null;

		if (param == null)
			param = new HashMap<String, Object>();

		param.put("uid", uid);

		return orderMapper.getSplitOrderList(param);
	}

}
