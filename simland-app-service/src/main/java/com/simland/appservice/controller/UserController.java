package com.simland.appservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simland.appservice.base.Constants;
import com.simland.core.base.SysMessage;
import com.simland.core.base.Utils;
import com.simland.core.module.user.entity.User;
import com.simland.core.module.user.service.IUserService;

@Controller
public class UserController {

	public static final Log logger = LogFactory.getLog(UserController.class);

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/user/login")
	@ResponseBody
	public String login(HttpServletRequest request, Model model) {

		String reJson = null;
		SysMessage msg = new SysMessage();
		String callback = request.getParameter("callback");
		String uname = request.getParameter("uname");
		String upw = request.getParameter("upw");

		if (Utils.isObjectEmpty(uname) || Utils.isObjectEmpty(upw)) {
			msg.setCode("-1");
			msg.setMsg("用户名或密码不能为空");
			return reJson = Utils.objToJsonp(msg,
					request.getParameter("callback"));
		}

		User user = userService.login(uname, upw, msg);
		if (Utils.isObjectNotEmpty(user)) {
			request.getSession().setAttribute(Constants.USER_SESSION, user);
		}

		logger.info(this.getClass().getName() + " uname=" + uname + " "
				+ (reJson = Utils.objToJsonp(msg, callback)));
		msg = null;
		return reJson;
	}

	/***
	 * 是否登录
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user/isLogin")
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

		logger.info(this.getClass().getName()
				+ (reJson = Utils.objToJsonp(msg,
						request.getParameter("callback"))));

		return reJson;
	}
}
