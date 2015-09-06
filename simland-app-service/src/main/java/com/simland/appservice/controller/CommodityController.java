package com.simland.appservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simland.core.base.Utils;
import com.simland.core.module.shop.entity.Commodity;
import com.simland.core.module.shop.service.ICommodityService;

@Controller
public class CommodityController {

	public static final Log logger = LogFactory.getLog(CommodityController.class);

	@Autowired
	private ICommodityService commodityService;

	/***
	 * 商品展示
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/commodity/show")
	public String commodityShow(HttpServletRequest request, Model model) {

		String id = request.getParameter("id");

		Commodity commodity = commodityService.getCommodity(Utils.strToInteger(id));

		model.addAttribute("commodity", commodity);

		return "commodity/commodityShow";
	}

	/***
	 * 商品评论
	 * 
	 * @return
	 */
	@RequestMapping(value = "/commodity/assess")
	public String assess() {
		return "commodity/assess";
	}

	@RequestMapping(value = "/commodity/assess2")
	public String assess2() {
		return "commodity/assess2";
	}

}
