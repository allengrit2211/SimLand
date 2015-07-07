package com.simland.appservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {

	@RequestMapping(value = "/order/confirmOrder")
	public String confirmOrder() {

		return "order/confirmOrder";

	}

}
