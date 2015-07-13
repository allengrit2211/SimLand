package com.simland.core.module.order.service.impl;

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

}
