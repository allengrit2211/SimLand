package com.simland.backstage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminUserController {

	@RequestMapping(value = "/backstage/login")
	@ResponseBody
	public String login() {
		return "sdf";
	}

}
