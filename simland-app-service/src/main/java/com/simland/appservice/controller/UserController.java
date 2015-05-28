package com.simland.appservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simland.core.base.SysMessage;
import com.simland.core.base.Utils;
import com.simland.core.module.user.service.IUserService;

@Controller
public class UserController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/appservice/login")
	@ResponseBody
	public String login(HttpServletRequest request, Model model) {

		String callback = request.getParameter("callback");
		String uname = request.getParameter("uname");
		String upw = request.getParameter("upw");

		SysMessage msg = null;
		userService.login(uname, upw, msg = new SysMessage());

		System.out.println(Utils.toJsonpStr(msg, callback));

		return Utils.toJsonpStr(msg, callback);
	}

}
