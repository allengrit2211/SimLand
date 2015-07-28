package com.simland.core.module.order.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simland.core.base.SysMessage;
import com.simland.core.base.Utils;
import com.simland.core.module.order.entity.Cart;
import com.simland.core.module.order.entity.CartItem;
import com.simland.core.module.order.entity.Order;
import com.simland.core.module.order.entity.OrderItem;
import com.simland.core.module.order.entity.OrderStatus;
import com.simland.core.module.order.entity.PayStatus;
import com.simland.core.module.order.service.IOrderService;
import com.simland.core.module.order.service.IOrderState;
import com.simland.core.module.shop.entity.Shop;
import com.simland.core.module.user.entity.Address;
import com.simland.core.module.user.entity.User;

/***
 * 
 * ClassName: GeneralOrder
 * 
 * @Description: 普通订单处理
 * @author Gavin
 * @date 2015年7月9日
 */

@Component
public class GeneralOrder implements IOrderState {

	@Autowired
	private IOrderService orderService;

	@Override
	public int create(User user, Address address, Cart cart, SysMessage msg, String... remark) {

		List<Order> orders = new ArrayList<Order>();

		int index = 0;
		for (Entry<Shop, Vector<CartItem>> e : cart.getSettlementItems().entrySet()) {

			Order order = new Order();
			order.setUid(user.getId());
			order.setSid(e.getKey().getId());
			order.setTotal(1000d);
			order.setQuantity(100);
			order.setCreateTime(new Date());
			order.setUaid(address.getId());
			order.setReceiverName(address.getReceiverName());
			order.setReceiverPhone(address.getReceiverPhone());
			order.setReceiverProvince(address.getReceiverProvince());
			order.setReceiverCity(address.getReceiverCity());
			order.setReceiverDistrict(address.getReceiverDistrict());
			order.setReceiverAddress(address.getReceiverAddress());
			order.setReceiverZipCode(address.getReceiverZipCode());
			order.setOrderStatus(OrderStatus.NEW.getId());
			order.setPayStatus(PayStatus.PAY_WAIT.getId());
			order.setPayTime(new Date());
			order.setLogisticsStauts(0);// 物流状态，暂时未使用
			order.setRemark(Utils.getArrayVal(index, remark));

			index++;

			List<OrderItem> orderItems = new ArrayList<OrderItem>();
			for (CartItem ci : e.getValue()) {

				OrderItem orderItem = new OrderItem();
				orderItem.setCid(ci.getC().getId());
				orderItem.setCname(ci.getC().getName());
				orderItem.setAttr1Id(ci.getC().getAttr1().getId());
				orderItem.setAttr1Val(ci.getC().getAttr1Value());
				orderItem.setAttr2Id(ci.getC().getAttr2().getId());
				orderItem.setAttr2Val(ci.getC().getAttr2Value());
				orderItem.setBuyNum(ci.getBuyNum());
				orderItem.setCprice(ci.getPrice());
				orderItem.setCreateTime(new Date());

				orderItems.add(orderItem);
			}

			order.setOrderItems(orderItems);

			orders.add(order);
		}

		/****
		 * 库存检查
		 */

		try {
			orderService.insertOrder(orders);
		} catch (Exception e) {
			return -1;
		}
		return 1;
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
	public void complete(Order order) {

	}

}
