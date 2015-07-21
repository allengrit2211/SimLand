package com.simland.appservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simland.appservice.controller.security.SessionManager;
import com.simland.core.module.order.entity.Cart;
import com.simland.core.module.order.service.IOrderService;
import com.simland.core.module.user.entity.Address;
import com.simland.core.module.user.entity.User;
import com.simland.core.module.user.service.IAddressService;

@Controller
public class OrderController {

	public static final Log logger = LogFactory.getLog(OrderController.class);

	@Autowired
	private IAddressService addressService;

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
	@RequestMapping(value = "/order/submitOrder")
	public String submitOrder(HttpServletRequest request, Model model) {

		return null;
	}

}
