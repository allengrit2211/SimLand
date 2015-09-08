package com.simland.appservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simland.appservice.controller.security.SessionManager;
import com.simland.core.base.SysMessage;
import com.simland.core.base.Utils;
import com.simland.core.base.page.PageView;
import com.simland.core.module.order.entity.Cart;
import com.simland.core.module.order.entity.Order;
import com.simland.core.module.order.service.IOrderService;
import com.simland.core.module.order.service.IOrderState;
import com.simland.core.module.user.entity.Address;
import com.simland.core.module.user.entity.User;
import com.simland.core.module.user.service.IAddressService;

@Controller
public class OrderController {

	public static final Log logger = LogFactory.getLog(OrderController.class);

	@Autowired
	private IAddressService addressService;

	@Autowired
	private IOrderState generalOrder;

	@Autowired
	private IOrderService orderService;

	@RequestMapping(value = "/order/confirmOrder")
	public String confirmOrder(HttpServletRequest request, Model model) {

		String carChecks = request.getParameter("carCheck");
		logger.info("carChecks=" + carChecks);

		User user = SessionManager.getUser();
		Cart cart = user.getCart();

		model.addAttribute("cart", Cart.addSettlementItems(cart, carChecks == null ? null : carChecks.split(",")));

		Address address = addressService.getUserDefaultAddress(user.getId());
		model.addAttribute("address", address);

		return "order/confirmOrder";

	}

	/***
	 * 提交订单
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/order/submitOrder")
	public String submitOrder(HttpServletRequest request, Model model) {

		String reJson = null;
		SysMessage msg = new SysMessage();

		String addressId = request.getParameter("addressId");

		User user = SessionManager.getUser();
		Cart cart = user.getCart();

		Address userAddress = addressService.getAddress(Utils.strToInteger(addressId), user.getId());
		if (Utils.isObjectEmpty(userAddress)) {
			msg.setCode("-2");
			msg.setMsg("用户地址不存在");
			logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
			return reJson;
		}

		String[] remarks = request.getParameterValues("remarks");

		int id = generalOrder.create(user, userAddress, cart, msg, remarks);

		if (id > 0) {
			msg.setCode("1");
			msg.setMsg("订单创建成功");
		}

		logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));

		return reJson;
	}

	/****
	 * 取消订单
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/order/cancelOrder")
	public String cancelOrder(HttpServletRequest request, Model model) {

		String reJson = null;
		SysMessage msg = new SysMessage();

		int oid = Utils.strToInteger(request.getParameter("oid"));
		Order order = orderService.getOrder(oid);
		if (order == null) {
			msg.setCode("-1");
			msg.setMsg("订单不存在");
			logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
			return reJson;
		}

		try {
			int id = generalOrder.cancel(order, msg);
			if (id > 0) {
				msg.setCode("1");
				msg.setMsg("订单已取消");
				logger.info(this.getClass().getName()
						+ (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
				return reJson;
			} else {
				msg.setCode("-3");
				msg.setMsg("取消失败");
				logger.info(this.getClass().getName()
						+ (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
				return reJson;
			}
		} catch (Exception e) {
			logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
			e.printStackTrace();
			return reJson;
		}

	}

	/***
	 * 我的订单
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user/orders")
	public String myOrder(HttpServletRequest request, Model model) {

		User user = SessionManager.getUser();
		Map<String, Object> param = new HashMap<String, Object>();

		int currentPage = Utils.strToInteger(request.getParameter("icurrentPage"));

		param.put("uid", user.getId());
		int totalRecord = orderService.getOrderCount(param);

		PageView pageView = new PageView();
		pageView.setCurrentPage(currentPage);
		pageView.setPageSize(5);
		pageView.setTotalRecord(totalRecord);
		param.put("endSize", pageView.getFirstResult());
		param.put("pageSize", pageView.getPageSize());
		param.put("sortColumns", "id");
		List<Order> list = orderService.getSplitOrderList(user.getId(), param);
		model.addAttribute("list", list);
		return "user/orders";
	}
}
