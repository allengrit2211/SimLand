package com.simland.appservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simland.core.base.Utils;
import com.simland.core.module.shop.entity.Shop;
import com.simland.core.module.shop.service.IShopService;

@Controller
public class ShopController {

	@Autowired
	private IShopService shopService;

	@RequestMapping(value = "/shop/list")
	@ResponseBody
	public String list(HttpServletRequest request, Model model) {

		List<Shop> list = shopService.getShopList(null);

		System.out.println(list);

		System.out.println(Utils.listToJsonpStr(list,
				request.getParameter("callback")));

		return Utils.listToJsonpStr(list, request.getParameter("callback"));

	}

}
