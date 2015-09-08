package com.simland.core.module.order.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.simland.core.module.shop.entity.Inventory;
import com.simland.core.module.shop.entity.Shop;
import com.simland.core.module.shop.service.IInventoryService;
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

	public static final Log logger = LogFactory.getLog(GeneralOrder.class);

	@Autowired
	private IOrderService orderService;

	@Autowired
	private IInventoryService inventoryService;

	@Override
	public int create(User user, Address address, Cart cart, SysMessage msg, String... remark) {

		List<String> _waitClearSku = new LinkedList<String>();// 待清除购物车的商品

		List<Order> orders = new ArrayList<Order>();// 待生成的所有订单

		List<OrderItem> _lackOrderItem = new LinkedList<OrderItem>();// 库存不足商品

		int index = 0;
		for (Entry<Shop, Vector<CartItem>> e : cart.getSettlementItems().entrySet()) {

			List<OrderItem> orderItems = new ArrayList<OrderItem>();
			double totalMoney = 0;// 总金额
			int quantity = 0;// 商品总数量
			for (CartItem ci : e.getValue()) {
				OrderItem orderItem = new OrderItem();
				orderItem.setCid(ci.getC().getId());
				orderItem.setCname(ci.getC().getName());
				orderItem.setAttr1Id(Utils.strToInteger(ci.getC().getAttr1Val()));
				orderItem.setAttr1Val(ci.getC().getAttr1Value());
				orderItem.setAttr2Id(Utils.strToInteger(ci.getC().getAttr2Val()));
				orderItem.setAttr2Val(ci.getC().getAttr2Value());
				orderItem.setBuyNum(ci.getBuyNum());

				orderItem.setCprice(ci.getPrice());// 产品单价
				orderItem.setCreateTime(new Date());

				Inventory inventory = inventoryService.getInventoryNumsBySku(ci.getC().getId(), ci.getC().getSid(),
						Utils.strToInteger(ci.getC().getAttr1Val()), Utils.strToInteger(ci.getC().getAttr2Val()));
				if (inventory == null || inventory.getNums().intValue() < ci.getBuyNum()) {// 判断库存数量是否满足
					_lackOrderItem.add(orderItem);
				} else {
					_waitClearSku.add(ci.getSku());
					orderItem.setIid(inventory.getId());
					orderItems.add(orderItem);
				}

				totalMoney += ci.getPrice() * ci.getBuyNum();// 订单中总价格
				quantity += quantity + ci.getBuyNum();//订单中总数量
			}

			Order order = new Order();
			order.setUid(user.getId());
			order.setSid(e.getKey().getId());
			order.setTotal(totalMoney);
			order.setQuantity(quantity);
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
			order.setOrderItems(orderItems);
			index++;

			orders.add(order);
		}

		logger.info("GeneralOrder create: Order - " + Utils.objToJson(orders));

		/****
		 * 库存检查
		 */
		if (_lackOrderItem.size() > 0) {
			msg.setCode("-200");
			msg.setMsg(Utils.arrayToJson(_lackOrderItem));
			return -1;
		}

		orderService.insertOrder(orders);

		/****
		 * 清除购物车
		 */
		Cart.delCart(cart, _waitClearSku.toArray(new String[_waitClearSku.size()]));

		_waitClearSku = null;
		orders = null;

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
