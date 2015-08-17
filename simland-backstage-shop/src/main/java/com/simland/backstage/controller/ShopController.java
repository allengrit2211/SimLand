package com.simland.backstage.controller;

import java.lang.ProcessBuilder.Redirect;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.servlet.ModelAndView;

import com.simland.core.base.Constants;
import com.simland.core.base.Utils;
import com.simland.core.module.purview.entity.ShopUser;
import com.simland.core.module.shop.entity.Shop;
import com.simland.core.module.shop.service.IShopService;

/***
 * 商家控制类
 * 
 * @author Gavin
 *
 */
@Controller
public class ShopController {

	@Autowired
	private IShopService shopService;

	@RequestMapping(value = "/shop/editShopInfo")
	public ModelAndView editShopInfo(HttpServletRequest request, Model model) {

		ShopUser sessionShop = (ShopUser) request.getSession().getAttribute(Constants.USER_SESSION);
		String method = request.getParameter("method");
		Shop shop = shopService.getShop(sessionShop.getId());

		if (Utils.isObjectEmpty(method)) {
			model.addAttribute("method", "option");
			model.addAttribute("shop", shop);
			return new ModelAndView("shop/shopInfo", model.asMap());
		}

		if ("option".equals(method)) {

			if (Utils.isObjectEmpty(shop) || Utils.isObjectEmpty(shop.getId())) {
				request.setAttribute("mothod", null);
				model.addAttribute("msg", "店铺不存在");
				return new ModelAndView("shop/shopInfo", model.asMap());
			}

			String name = request.getParameter("name");
			if (Utils.isObjectNotEmpty(name))
				shop.setName(name);

			String cname = request.getParameter("cname");
			if (Utils.isObjectNotEmpty(cname))
				shop.setCname(cname);

			String caddress = request.getParameter("caddress");
			if (Utils.isObjectNotEmpty(caddress))
				shop.setCaddress(caddress);

			String bmodel = request.getParameter("bmodel");
			if (Utils.isObjectNotEmpty(bmodel))
				shop.setBmodel(bmodel);

			String brand = request.getParameter("brand");
			if (Utils.isObjectNotEmpty(brand))
				shop.setBrand(brand);

			String salesArea = request.getParameter("salesArea");
			if (Utils.isObjectNotEmpty(salesArea))
				shop.setSalesArea(salesArea);

			String clientrGroup = request.getParameter("clientrGroup");
			if (Utils.isObjectNotEmpty(clientrGroup))
				shop.setClientrGroup(clientrGroup);

			String regAddress = request.getParameter("regAddress");
			if (Utils.isObjectNotEmpty(regAddress))
				shop.setRegAddress(regAddress);

			String corporate = request.getParameter("corporate");
			if (Utils.isObjectNotEmpty(corporate))
				shop.setCorporate(corporate);

			String people = request.getParameter("people");
			if (Utils.isObjectNotEmpty(people))
				shop.setPeople(people);

			String engage = request.getParameter("engage");
			if (Utils.isObjectNotEmpty(engage))
				shop.setEngage(engage);

			String phone = request.getParameter("phone");
			if (Utils.isObjectNotEmpty(phone))
				shop.setPhone(phone);

			String contact = request.getParameter("contact");
			if (Utils.isObjectNotEmpty(contact))
				shop.setContact(contact);

			String contactPeople = request.getParameter("contactPeople");
			if (Utils.isObjectNotEmpty(contactPeople))
				shop.setContactPeople(contactPeople);

			String email = request.getParameter("email");
			if (Utils.isObjectNotEmpty(email))
				shop.setEmail(email);

			String clogo = request.getParameter("clogo");
			if (Utils.isObjectNotEmpty(clogo))
				shop.setClogo(clogo);

			String licenseImg = request.getParameter("licenseImg");
			if (Utils.isObjectNotEmpty(licenseImg))
				shop.setLicenseImg(licenseImg);

			shopService.updateShop(shop);
		}

		request.setAttribute("mothod", null);
		return new ModelAndView("redirect:/shop/editShopInfo", model.asMap());
	}

}
