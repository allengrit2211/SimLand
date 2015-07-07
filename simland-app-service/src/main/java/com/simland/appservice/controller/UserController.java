package com.simland.appservice.controller;

import java.lang.ProcessBuilder.Redirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simland.appservice.base.Constants;
import com.simland.core.base.SysMessage;
import com.simland.core.base.Utils;
import com.simland.core.module.user.entity.User;
import com.simland.core.module.user.service.ICollectShopService;
import com.simland.core.module.user.service.IUserService;

@Controller
public class UserController {

	public static final Log logger = LogFactory.getLog(UserController.class);

	@Autowired
	private IUserService userService;

	@Autowired
	private ICollectShopService collectShopService;

	/****
	 * 登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loginPage")
	public String login() {
		return "login";
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
		UserDetails userDetails = userService.loadUserByUsername(uname);
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
		HttpSession session = request.getSession(true);
		session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);


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
		Object obj = request.getSession().getAttribute(Constants.USER_SESSION);
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

}
