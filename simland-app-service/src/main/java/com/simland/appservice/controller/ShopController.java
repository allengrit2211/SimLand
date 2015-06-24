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
import com.simland.core.module.shop.entity.Commodity;
import com.simland.core.module.shop.entity.Shop;
import com.simland.core.module.shop.service.ICommodityService;
import com.simland.core.module.shop.service.IShopService;

@Controller
public class ShopController {

	public static final Log logger = LogFactory.getLog(ShopController.class);

	@Autowired
	private IShopService shopService;

	@Autowired
	private ICommodityService commodityService;

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
		String stype = request.getParameter("stype");// 搜索类型

		Map<String, Object> json = new HashMap<String, Object>();

		int currentPage = Utils.strToInteger(request
				.getParameter("icurrentPage"));

		Map<String, Object> param = new HashMap<String, Object>();

		if ("score".equalsIgnoreCase(sort))
			param.put("sortColumns", "score");
		param.put("sortType", sortType);

		if ("1".equals(stype))
			param.put("engageLike", (k + "").trim());
		else
			param.put("cnameLike", (k + "").trim());

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

	/***
	 * 店铺新品列表
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/shop/commodityList")
	@ResponseBody
	public String commodityNew(HttpServletRequest request, Model model) {

		String reJson = null;

		Map<String, Object> json = new HashMap<String, Object>();

		String type = request.getParameter("type");// 0 新品 1特价

		String sid = request.getParameter("sid");
		if (Utils.isObjectEmpty(sid)) {
			json.put("code", "-1");
			reJson = Utils.objToJsonp(json, request.getParameter("callback"));
			return reJson;
		}

		int currentPage = Utils.strToInteger(request
				.getParameter("icurrentPage"));

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sid", sid);
		
		if ("0".equals(type)) {
			param.put("isNew", "1");
		}
		if ("1".equals(type)) {
			param.put("isSpecial", "1");
		}

		int totalRecord = commodityService.getCommodityCount(param);

		PageView pageView = new PageView();
		pageView.setCurrentPage(currentPage);
		pageView.setPageSize(100);
		pageView.setTotalRecord(totalRecord);
		param.put("endSize", pageView.getFirstResult());
		param.put("pageSize", pageView.getPageSize());

		List<Commodity> list = commodityService.getSplitCommodityList(param);

		json.put("code", "1");
		json.put("totalPage", pageView.getTotalPage());
		json.put("list", list);

		logger.info(reJson = Utils.objToJsonp(json,
				request.getParameter("callback")));

		param.clear();
		list.clear();

		return reJson;
	}
}
