package com.simland.backstage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simland.backstage.util.Constants;
import com.simland.core.module.shop.entity.Shop;
import com.simland.core.module.shop.service.ICategoryPropertiesService;
import com.simland.core.module.shop.service.ICommodityService;

@Controller
public class CommodityController {

	@Autowired
	private ICommodityService commodityService;

	@Autowired
	private ICategoryPropertiesService categoryPropertiesService;

	@RequestMapping(value = "/commodity/addShow")
	public String addCommodityShow(HttpServletRequest request, Model model) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pid", 0);
		List list = categoryPropertiesService.getCategoryPropertiesList(param);
		model.addAttribute("cplist", list);

		return "commodity/addCommodity";
	}

	@RequestMapping(value = "/commodity/list")
	public String list(HttpServletRequest request, Model model) {

		Shop shop = (Shop) request.getSession().getAttribute(
				Constants.USER_SESSION);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sid", shop.getId());
		List list = commodityService.getCommodityList(param);
		model.addAttribute("list", list);
		return "commodity/listCommodity";
	}
}
