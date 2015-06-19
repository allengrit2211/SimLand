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

	/***
	 * 店铺列表信息
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/shop/list")
	@ResponseBody
	public String list(HttpServletRequest request, Model model) {

		String sort = request.getParameter("sort");
		String sortType = request.getParameter("sortType");
		String k = request.getParameter("k");

		Map<String, Object> json = new HashMap<String, Object>();

		int currentPage = Utils.strToInteger(request
				.getParameter("icurrentPage"));

		Map<String, Object> param = new HashMap<String, Object>();

		if ("score".equalsIgnoreCase(sort))
			param.put("sortColumns", "score");
		param.put("sortType", sortType);
		param.put("cnameLike", k);

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

		String reJson = null;
		logger.info(reJson = Utils.objToJsonp(json,
				request.getParameter("callback")));

		json = null;
		param = null;
		pageView = null;
		list = null;

		return reJson;

	}

	/**
	 * 店铺详情页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/shop/showShop")
	@ResponseBody
	public String showShop(HttpServletRequest request, Model model) {

		Shop shop = shopService.getShop(Utils.strToInteger(request
				.getParameter("id")));

		String reJson = null;
		logger.info(reJson = Utils.objToJsonp(shop,
				request.getParameter("callback")));

		shop = null;

		return reJson;

	}

}
