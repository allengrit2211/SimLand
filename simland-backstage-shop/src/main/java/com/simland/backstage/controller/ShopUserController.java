package com.simland.backstage.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.simland.core.base.Constants;
import com.simland.core.base.MD5Util;
import com.simland.core.base.Utils;
import com.simland.core.base.page.PageView;
import com.simland.core.module.purview.entity.Power;
import com.simland.core.module.purview.entity.ShopUser;
import com.simland.core.module.purview.service.IPowerService;
import com.simland.core.module.purview.service.IShopUserService;

@Controller
public class ShopUserController {

	private static final Log log = LogFactory.getLog(ShopUserController.class);

	@Autowired
	private IShopUserService shopUserService;

	@Autowired
	private IPowerService powerService;

	@RequestMapping(value = "/purview/shopUserAdd")
	public String shopUserAdd(HttpServletRequest request, Model model) {

		String id = request.getParameter("id");
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		String passWord1 = request.getParameter("passWord1");
		String rid = request.getParameter("rid");
		String type = request.getParameter("type");

		if (!validate(userName, passWord, passWord1, rid, type, model)) {
			return "redirect:/purview/shopUserList?id=" + id;
		}

		int idv = 0;
		int i = 0;
		ShopUser shopUser = new ShopUser();
		shopUser.setUserName(Utils.notNullTrim(userName));
		shopUser.setPassWord(MD5Util.md5Hex(Utils.notNullTrim(passWord)));
		shopUser.setRid(Utils.strToInteger(rid));
		shopUser.setType(Utils.strToInteger(type));

		if (Utils.isObjectNotEmpty(id) && (idv = Utils.strToInteger(id)) != 0) {
			shopUser.setId(idv);
			i = shopUserService.updateShopUser(shopUser);
		} else {
			i = shopUserService.insertShopUser(shopUser);
		}
		model.addAttribute("msg", (i > 0) ? "操作成功" : "操作失败");
		return "redirect:/purview/shopUserList";

	}

	public boolean validate(String userName, String passWord, String passWord1, String rid, String type, Model model) {
		if (Utils.isObjectEmpty(userName)) {
			model.addAttribute("msg", "用户名不能为空");
			return false;
		}

		if (Utils.isObjectEmpty(passWord)) {
			model.addAttribute("msg", "密码不能为空");
			return false;
		}
		if (!passWord.equals(passWord1)) {
			model.addAttribute("msg", "确认密码与密码不相同");
			return false;
		}
		if (Utils.strToInteger(rid) <= 0) {
			model.addAttribute("msg", "角色输入不正确");
			return false;
		}
		if (Utils.isObjectEmpty(type)) {
			model.addAttribute("msg", "用户类型输入不正确");
			return false;
		}
		return true;
	}

	@RequestMapping(value = "/purview/shopUserList")
	public String shopUserList(HttpServletRequest request, Model model, PageView pageView) {

		showEdit(request, model);

		Map<String, Object> param = new HashMap<String, Object>();

		int totalRecord = shopUserService.getShopUserCount(param);
		if (totalRecord == 0) {
			return "purview/shopUserList";
		}

		pageView.setPageSize(10);

		param.put("endSize", pageView.getFirstResult());
		param.put("pageSize", pageView.getPageSize());

		List list = shopUserService.getSplitShopUserList(param);

		pageView.setTotalRecord(totalRecord);
		pageView.setRecords(list);

		model.addAttribute("pageView", pageView);
		model.addAttribute("msg", request.getParameter("msg"));
		return "purview/shopUserList";
	}

	private void showEdit(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");

		if (Utils.isObjectNotEmpty(id)) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("id", id);
			ShopUser au = shopUserService.getShopUser(param);
			model.addAttribute("shopUser", Utils.isObjectEmpty(au) ? new ShopUser() : au);
			model.addAttribute("id", id);
		}
	}

	@RequestMapping(value = "/purview/shopUserDel")
	public String shopUserDel(HttpServletRequest request, Model model, PageView pageView) {
		String id = request.getParameter("id");
		if (Utils.isObjectNotEmpty(id) && Utils.strToInteger(id) != 0) {
			shopUserService.deleteShopUser(Utils.strToInteger(id));
		}
		return "redirect:/purview/shopUserList";
	}

	@RequestMapping(value = "/purview/permissions")
	public String permissions() {
		return "purview/permissions";
	}
	

	@RequestMapping(value = "/shop/login")
	public ModelAndView login(HttpServletRequest request, Model model) {

		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		String method = request.getParameter("method");
		if (Utils.isObjectEmpty(method)) {
			model.addAttribute("method", "option");
			return new ModelAndView("login", model.asMap());
		}

		if ("option".equals(method)) {
			if (Utils.isObjectEmpty(passWord) || Utils.isObjectEmpty(passWord)) {
				model.addAttribute("msg", "用户名或密码不能为空");
				model.addAttribute("method", "option");
				return new ModelAndView("login", model.asMap());
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userName", userName);
			ShopUser ua = shopUserService.getShopUser(params);
			if (Utils.isObjectEmpty(ua)) {
				model.addAttribute("msg", "用户名或密码不存在");
				model.addAttribute("method", "option");
				return new ModelAndView("login", model.asMap());
			}
			if (!MD5Util.md5Hex(passWord).equalsIgnoreCase(ua.getPassWord())) {
				model.addAttribute("msg", "用户名或密码错误");
				model.addAttribute("method", "option");
				return new ModelAndView("login", model.asMap());
			}

			List<Power> list = null;
			if (ua.getType() == 1) {
				list = powerService.getPowerList(null);
			} else {
				if (Utils.isObjectNotEmpty(ua.getRid())) {
					list = powerService.getPowerListByRid(ua.getRid());
				}
			}

			Map<String, String> powers = new HashMap<String, String>();
			for (int i = 0; Utils.isObjectNotEmpty(list) && i < list.size(); i++) {
				Power p = list.get(i);
				powers.put(p.getUrl(), p.getId() + "_" + p.getName());
			}
			ua.setPowers(powers);
			request.getSession().setAttribute(Constants.USER_SESSION, ua);
			return new ModelAndView("redirect:/main");
		}
		model.addAttribute("method", "option");
		return new ModelAndView("login", model.asMap());
	}

	@RequestMapping(value = "/shop/loginout")
	public ModelAndView loginout(HttpServletRequest request) {
		request.getSession().removeAttribute(Constants.USER_SESSION);
		return new ModelAndView("redirect:/shop/login");
	}
}
