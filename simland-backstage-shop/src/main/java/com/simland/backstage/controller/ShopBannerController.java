package com.simland.backstage.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.simland.core.base.Constants;
import com.simland.core.base.ConstantsImage;
import com.simland.core.base.Utils;
import com.simland.core.module.purview.entity.ShopUser;
import com.simland.core.module.shop.entity.ShopBanner;
import com.simland.core.module.shop.service.IShopBannerService;

@Controller
public class ShopBannerController {

	public static final Log logger = LogFactory.getLog(ShopBannerController.class);

	@Autowired
	private IShopBannerService shopBannerService;

	@RequestMapping(value = "/shop/bannerList")
	public ModelAndView list(HttpServletRequest request, Model model) {

		ShopUser shopUser = (ShopUser) request.getSession().getAttribute(Constants.USER_SESSION);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sid", shopUser.getId());

		param.put("endSize", 3);
		param.put("pageSize", 0);

		List list = shopBannerService.getShopBannerList(param);

		model.addAttribute("list", list);
		return new ModelAndView("shop/bannerList");
	}

	@RequestMapping(value = "/shop/bannerEdit")
	public ModelAndView bannerAdd(HttpServletRequest request, Model model) {

		String[] ids = request.getParameterValues("ids");
		String[] picUrl = request.getParameterValues("picUrl");
		String[] linkUrl = request.getParameterValues("linkUrl");

		if (Utils.isObjectEmpty(ids) || ids.length == 0 || Utils.isObjectEmpty(linkUrl) || linkUrl.length == 0) {
			model.addAttribute("msg", "参数错误");
			return list(request, model);
		}

		ShopUser shopUser = (ShopUser) request.getSession().getAttribute(Constants.USER_SESSION);

		List<ShopBanner> sBanners = new LinkedList<ShopBanner>();
		for (int i = 0; i < ids.length; i++) {
			ShopBanner sBanner = new ShopBanner();
			sBanner.setId(Utils.strToInteger(Utils.getArrayVal(i, ids)));
			String p = Utils.getArrayVal(i, picUrl);
			if(Utils.isObjectNotEmpty(p)){
				sBanner.setPicUrl(ConstantsImage.copyFile(request, ConstantsImage.SHOP_BANNER, Utils.getArrayVal(i, picUrl)));
			}
			sBanner.setLinkUrl(Utils.getArrayVal(i, linkUrl));
			sBanner.setSid(shopUser.getSid());
			sBanners.add(sBanner);
		}

		shopBannerService.insertShopBanner(sBanners);

		return new ModelAndView("redirect:/shop/bannerList");
	}
}
