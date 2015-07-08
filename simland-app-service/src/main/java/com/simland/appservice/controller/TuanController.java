package com.simland.appservice.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Title: TuanController.java
 * @Package com.simland.appservice.controller
 * @Description: 团购控制层类
 * @author Gavin
 * @date 2015年7月8日 上午9:55:04
 * @version V1.0
 */

@Controller
public class TuanController {

	public static final Log logger = LogFactory.getLog(TuanController.class);

	@RequestMapping(value = "/tuan/list")
	public String list() {

		return "tuan/tuanList";
	}

}
