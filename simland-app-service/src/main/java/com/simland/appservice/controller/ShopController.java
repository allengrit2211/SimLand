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

import com.simland.core.base.Utils;
import com.simland.core.base.page.PageView;
import com.simland.core.module.shop.entity.Shop;
import com.simland.core.module.shop.service.IShopService;

@Controller
public class ShopController {

	public static final Log logger = LogFactory.getLog(ShopController.class);

	@Autowired
	private IShopService shopService;

	@RequestMapping(value = "/shop/list")
	@ResponseBody
	public String list(HttpServletRequest request, Model model) {

		Map<String, Object> json = new HashMap<String, Object>();

		int currentPage = Utils.strToInteger(request
				.getParameter("currentPage"));

		Map<String, Object> param = new HashMap<String, Object>();

		int totalRecord = shopService.getShopCount(param);

		PageView pageView = new PageView();
		pageView.setCurrentPage(currentPage);
		pageView.setPageSize(5);
		pageView.setTotalRecord(totalRecord);

		param.put("endSize", pageView.getFirstResult());
		param.put("pageSize", pageView.getPageSize());

		List<Shop> list = shopService.getSplitShopList(param);

		json.put("totalPage", pageView.getTotalPage());
		json.put("list", list);

		System.out.println(Utils.objToJson(json));

		System.out.println(pageView.getTotalPage());

		String reJson = null;
		logger.info(reJson = Utils.objToJsonp(json,
				request.getParameter("callback")));

		json = null;
		param = null;
		pageView = null;
		list = null;

		return reJson;

	}

}
