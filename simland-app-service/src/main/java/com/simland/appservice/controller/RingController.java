package com.simland.appservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simland.core.base.Utils;
import com.simland.core.module.smap.entity.Ring;
import com.simland.core.module.smap.service.IRingService;

@Controller
public class RingController {

	public static final Log logger = LogFactory.getLog(RingController.class);

	@Autowired
	private IRingService ringService;

	@RequestMapping(value = "/smap/preview")
	public String add(HttpServletRequest request, Model model) {
		List<Ring> list = ringService.getRingList(null);
		model.addAttribute("ringlist", Utils.arrayToJson(list));
		return "map/map1Page";
	}
}
