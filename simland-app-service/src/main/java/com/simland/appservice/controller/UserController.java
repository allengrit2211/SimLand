package com.simland.appservice.controller;

import java.lang.ProcessBuilder.Redirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simland.appservice.controller.security.SessionManager;
import com.simland.core.base.Constants;
import com.simland.core.base.SysMessage;
import com.simland.core.base.Utils;
import com.simland.core.module.user.entity.Address;
import com.simland.core.module.user.entity.User;
import com.simland.core.module.user.service.IAddressService;
import com.simland.core.module.user.service.ICollectShopService;
import com.simland.core.module.user.service.IUserService;

@Controller
public class UserController {

	public static final Log logger = LogFactory.getLog(UserController.class);

	@Autowired
	private IUserService userService;

	@Autowired
	private ICollectShopService collectShopService;

	@Autowired
	private IAddressService addressService;

	/****
	 * 登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loginPage")
	public String login(HttpServletRequest request) {

		System.out.println(request.getQueryString());

		String reJson = null;
		String ajax = request.getParameter("ajax");
		if ("ajax".equalsIgnoreCase(ajax)) {
			SysMessage msg = new SysMessage();
			logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
			msg = null;
			return reJson;
		} else {
			return "login";
		}

	}

	/**
	 * 退出
	 * 
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String logout() {
		logger.debug("logout");
		return "logout";
	}

	/***
	 * 用户中心
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user/userCenter")
	public String userCenter() {
		return "user/userCenter";
	}

	/***
	 * 消息中心
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user/messageCenter")
	public String messageCenter() {
		return "user/messageCenter";
	}

	@RequestMapping(value = "/loginAjax")
	@ResponseBody
	public String loginAjax(HttpServletRequest request, Model model) {
		SysMessage msg = new SysMessage();
		String uname = request.getParameter("uname");
		String upw = request.getParameter("upw");

		String reJson = null;
		if (Utils.isObjectEmpty(uname) || Utils.isObjectEmpty(upw)) {
			msg.setCode("-1");
			msg.setMsg("用户名或密码不能为空");
			logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
			return reJson;
		}

		// User user = userService.login(uname, upw, msg);
		// if (Utils.isObjectNotEmpty(user)) {
		// request.getSession().setAttribute(Constants.USER_SESSION, user);
		// }

		// spring security 将权限及用户信息存入securityContext
		System.out.println("start ");
		UserDetails userDetails = userService.loadUserByUsername(uname);
		if (Utils.isObjectEmpty(userDetails)) {
			msg.setCode("-2");
			msg.setMsg("用户名或密码不存在");
			logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
			return reJson;
		}

		System.out.println("userDetails end" + userDetails);
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities()));
		HttpSession session = request.getSession(true);
		session.setAttribute(Constants.SPRING_SECURITY_CONTEXT, securityContext);

		msg.setCode("1");
		msg.setMsg("登录成功");
		logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
		return reJson;
	}

	@RequestMapping(value = "/logindo")
	public String logindo(HttpServletRequest request, Model model) {

		SysMessage msg = new SysMessage();
		String uname = request.getParameter("uname");
		String upw = request.getParameter("upw");
		String toUrl = request.getParameter("toUrl");

		if (Utils.isObjectEmpty(uname) || Utils.isObjectEmpty(upw)) {
			msg.setCode("-1");
			msg.setMsg("用户名或密码不能为空");
			return "login";
		}

		// User user = userService.login(uname, upw, msg);
		// if (Utils.isObjectNotEmpty(user)) {
		// request.getSession().setAttribute(Constants.USER_SESSION, user);
		// }

		// spring security 将权限及用户信息存入securityContext
		System.out.println("start ");
		UserDetails userDetails = userService.loadUserByUsername(uname);
		if (Utils.isObjectEmpty(userDetails)) {
			msg.setCode("-2");
			msg.setMsg("用户名或密码不存在");
			return "login";
		}

		System.out.println("userDetails end" + userDetails);
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities()));
		HttpSession session = request.getSession(true);
		session.setAttribute(Constants.SPRING_SECURITY_CONTEXT, securityContext);

		System.out.println("-----------------" + "redirect:" + toUrl);
		if (Utils.isObjectNotEmpty(toUrl)) {
			return "redirect:" + toUrl;
		}

		return "index";
	}

	/***
	 * 是否登录
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/isLogin")
	@ResponseBody
	public String isLogin(HttpServletRequest request, Model model) {
		String reJson = null;

		SysMessage msg = new SysMessage();
		Object obj = request.getSession().getAttribute(Constants.SPRING_SECURITY_CONTEXT);
		if (Utils.isObjectNotEmpty(obj)) {
			msg.setCode("1");
			msg.setMsg("已登录");
		} else {
			msg.setCode("2");
			msg.setMsg("未登录");
		}

		logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));

		return reJson;
	}

	/***
	 * 添加用户地址显示
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user/addAddressShow")
	public String addAddressShow(HttpServletRequest request, Model model) {
		model.addAttribute("toUrl", request.getHeader("referer"));
		return "user/addAddress";
	}

	/***
	 * 添加地址
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user/addAddress")
	public String addAddress(HttpServletRequest request, Model model) {
		Address address = new Address();
		address.setIsDefault(1);
		address.setUid(SessionManager.getUser().getId());
		address.setReceiverName(request.getParameter("receiverName"));
		address.setReceiverPhone(request.getParameter("receiverPhone"));
		address.setReceiverCity(request.getParameter("receiverCity"));
		address.setReceiverAddress(request.getParameter("receiverAddress"));
		address.setReceiverZipCode(request.getParameter("receiverZipCode"));

		addressService.insertAddress(address);
		String toUrl = request.getParameter("toUrl");
		if (Utils.isObjectNotEmpty(Utils.notNullTrim(toUrl))) {
			return  "redirect:"+toUrl;
		} else
			return "order/confirmOrder";
	}

}
