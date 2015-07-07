package com.simland.appservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartController {

	@RequestMapping(value = "/buy/cart")
	public String showShoppingCar() {

		return "buy/cart";
	}

}
