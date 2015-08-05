package com.simland.backstage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.simland.core.base.SysMessage;
import com.simland.core.base.Utils;
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

	@RequestMapping(value = "/shop/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/shop/logindo")
	public ModelAndView logindo(HttpServletRequest request, Model model) {

		String loginName = request.getParameter("userName");
		String pwd = request.getParameter("passWord");
		if (Utils.isObjectEmpty(loginName) || Utils.isObjectEmpty(pwd)) {
			model.addAttribute("msg", "用户名或密码不能为空");
			return new ModelAndView("login", model.asMap());
		}

		SysMessage msg = new SysMessage();
		Shop shop = shopService.shopLogin(loginName, pwd, msg);

		if (Utils.isObjectEmpty(shop)) {
			model.addAttribute("msg", "用户名或密码错误");
			return new ModelAndView("login", model.asMap());
		}

		return new ModelAndView("index.jsp");
	}

}
