package com.simland.appservice.controller;

import javax.servlet.http.HttpServletRequest;

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

	@Autowired
	private IAddressService addressService;

	@Autowired
	private IOrderService orderService;

	@RequestMapping(value = "/order/confirmOrder")
	public String confirmOrder(Model model) {

		User user = SessionManager.getUser();
		Cart cart = user.getCart();
		model.addAttribute("cart", cart);

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
