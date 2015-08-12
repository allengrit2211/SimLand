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

import com.simland.core.base.Utils;
import com.simland.core.base.page.PageView;
import com.simland.core.module.purview.entity.Power;
import com.simland.core.module.purview.service.IPowerService;

@Controller
public class PowerController {

	private static final Log logger = LogFactory.getLog(PowerController.class);

	@Autowired
	private IPowerService powerService;

	@RequestMapping(value = "/purview/powerAdd")
	public String addShow(HttpServletRequest request, Model model) {

		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String url = request.getParameter("url");
		String pid = request.getParameter("pid");

		if (!validate(name, url, pid)) {
			model.addAttribute("msg", "参数不能为空");
			return "redirect:/purview/powerList";
		}

		int idv = 0;
		int i = 0;
		Power power = new Power(null, Utils.notNullTrim(name), Utils.notNullTrim(url), Utils.strToInteger(pid));
		if (Utils.isObjectNotEmpty(id) && (idv = Utils.strToInteger(id)) != 0) {
			power.setId(idv);
			i = powerService.updatePower(power);
		} else {
			i = powerService.insertPower(power);
		}
		model.addAttribute("msg", (i > 0) ? "操作成功" : "操作失败");
		return "redirect:/purview/powerList";

	}

	public boolean validate(String name, String url, String pid) {
		if (Utils.isObjectEmpty(name) || Utils.isObjectEmpty(url) || Utils.isObjectEmpty(pid))
			return false;
		else
			return true;
	}

	@RequestMapping(value = "/Power/del")
	public String del() {
		return "/Power/del";
	}

	@RequestMapping(value = "/Power/update")
	public String update() {
		return "/Power/update";
	}

	@RequestMapping(value = "/purview/powerList")
	public String list(HttpServletRequest request, Model model, PageView pageView) {

		String nameLike = request.getParameter("nameLike");
		String msg = request.getParameter("msg");
		// 显示编辑信息
		this.editShow(request, model);

		Map<String, Object> param = new HashMap<String, Object>();

		if (Utils.isObjectNotEmpty(nameLike))
			param.put("nameLike", nameLike);

		int totalRecord = powerService.getPowerCount(param);
		if (totalRecord == 0) {
			model.addAttribute("msg", "无数据");
			return "purview/powerList";
		}

		pageView.setPageSize(10);

		param.put("endSize", pageView.getFirstResult());
		param.put("pageSize", pageView.getPageSize());

		List list = powerService.getSplitPowerList(param);

		pageView.setTotalRecord(totalRecord);
		pageView.setRecords(list);

		model.addAttribute("msg", msg);
		model.addAttribute("nameLike", nameLike);
		model.addAttribute("pageView", pageView);

		return "/purview/powerList";
	}

	private void editShow(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");

		if (Utils.isObjectNotEmpty(id)) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("id", id);
			Power power = powerService.getPower(param);
			model.addAttribute("power", Utils.isObjectEmpty(power) ? new Power() : power);
			model.addAttribute("id", id);
		}
	}

}
