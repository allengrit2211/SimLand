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

import com.simland.core.base.Utils;
import com.simland.core.base.page.PageView;
import com.simland.core.module.shop.entity.Commodity;
import com.simland.core.module.shop.entity.Inventory;
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
	public String list(HttpServletRequest request, Model model) {

		String sort = request.getParameter("sort");
		String sortType = request.getParameter("sortType");
		String k = request.getParameter("k");
		String stype = request.getParameter("stype");// 搜索类型

		int currentPage = Utils.strToInteger(request.getParameter("icurrentPage"));

		Map<String, Object> param = new HashMap<String, Object>();

		if ("score".equalsIgnoreCase(sort))
			param.put("sortColumns", "score");
		param.put("sortType", sortType);

		if ("1".equals(stype) && Utils.isObjectNotEmpty(k)) {
			param.put("engageLike", (k + "").trim());
		}

		if ("0".equals(stype) && Utils.isObjectNotEmpty(k)) {
			param.put("cnameLike", (k + "").trim());
		}

		int totalRecord = shopService.getShopCount(param);

		PageView pageView = new PageView();
		pageView.setCurrentPage(currentPage);
		pageView.setPageSize(5);
		pageView.setTotalRecord(totalRecord);
		param.put("endSize", pageView.getFirstResult());
		param.put("pageSize", pageView.getPageSize());

		List<Shop> list = shopService.getSplitShopList(param);

		model.addAttribute("totalPage", pageView.getTotalPage());
		model.addAttribute("list", list);
		model.addAttribute("sort", sort);
		model.addAttribute("sortType", sortType);
		model.addAttribute("k", k);
		model.addAttribute("stype", stype);

		if (totalRecord == 0) {// 搜索结果为0时默认显示
			param.clear();
			param.put("endSize", pageView.getFirstResult());
			param.put("pageSize", pageView.getPageSize());
			model.addAttribute("totalPage", pageView.getTotalPage());
			List<Shop> list1 = shopService.getSplitShopList(param);
			model.addAttribute("list1", list1);
		}

		param = null;
		pageView = null;
		list = null;

		return "shop/sellerList";

	}

	@RequestMapping(value = "/shop/listAjax")
	public String listAjax(HttpServletRequest request, Model model) {
		this.list(request, model);
		return "shop/sellerListAjax";
	}

	/**
	 * 店铺详情页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/shop/showShop")
	public String showShop(HttpServletRequest request, Model model) {

		Shop shop = shopService.getShop(Utils.strToInteger(request.getParameter("id")));

		// String reJson = null;
		// logger.info(reJson = Utils.objToJsonp(shop,
		// request.getParameter("callback")));

		model.addAttribute("shop", shop);

		if (Utils.isObjectNotEmpty(shop)) {
			model.addAttribute("list1", shopCommodity("0", shop.getId()));
			model.addAttribute("list2", shopCommodity("1", shop.getId()));
			model.addAttribute("list3", shopCommodity(null, shop.getId()));
		}

		return "/shop/shopShow";

	}

	/***
	 * 店铺新品列表
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	private List<Commodity> shopCommodity(String type, Integer sid) {

		if (Utils.isObjectEmpty(sid))
			return null;

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sid", sid);

		if ("0".equals(type)) {
			param.put("isNew", "1");
		}
		if ("1".equals(type)) {
			param.put("isSpecial", "1");
		}

		param.put("status", Commodity.status_1);
		int totalRecord = commodityService.getCommodityCount(param);

		PageView pageView = new PageView();
		pageView.setCurrentPage(1);
		pageView.setPageSize(100);
		pageView.setTotalRecord(totalRecord);
		param.put("endSize", pageView.getFirstResult());
		param.put("pageSize", pageView.getPageSize());

		return commodityService.getSplitCommodityList(param);

	}

	/****
	 * 联系方式
	 * 
	 * @return
	 */
	@RequestMapping(value = "/shop/contact")
	public String contactPage(HttpServletRequest request, Model model) {

		Shop shop = shopService.getShop(Utils.strToInteger(request.getParameter("id")));

		model.addAttribute("shop", shop);

		return "shop/shopContact";
	}

	/****
	 * 详细信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/shop/info")
	public String info(HttpServletRequest request, Model model) {

		Shop shop = shopService.getShop(Utils.strToInteger(request.getParameter("id")));

		model.addAttribute("shop", shop);

		return "shop/shopInfo";
	}

}
