package com.simland.backstage.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.simland.backstage.util.Constants;
import com.simland.core.base.SysMessage;
import com.simland.core.base.Utils;
import com.simland.core.module.shop.entity.CategoryPropertiesVal;
import com.simland.core.module.shop.entity.Commodity;
import com.simland.core.module.shop.entity.Inventory;
import com.simland.core.module.shop.entity.Shop;
import com.simland.core.module.shop.service.ICategoryPropertiesService;
import com.simland.core.module.shop.service.ICommodityService;
import com.simland.core.module.shop.service.IInventoryService;

@Controller
public class CommodityController {

	public static final Log logger = LogFactory.getLog(CommodityController.class);

	@Autowired
	private ICommodityService commodityService;

	@Autowired
	private ICategoryPropertiesService categoryPropertiesService;

	@Autowired
	private IInventoryService inventoryService;

	@RequestMapping(value = "/commodity/addShow")
	public String addCommodityShow(HttpServletRequest request, Model model) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pid", 0);
		List list = categoryPropertiesService.getCategoryPropertiesList(param);
		model.addAttribute("cplist", list);

		return "commodity/addCommodity";
	}

	@RequestMapping(value = "/commodity/addCommodity")
	@ResponseBody
	public String addommodity(HttpServletRequest request, Model model) {

		String[] attr1 = request.getParameterValues("attr1");
		String[] attr1Val = request.getParameterValues("attr1Val");
		String[] attr2 = request.getParameterValues("attr2");
		String[] attr2Val = request.getParameterValues("attr2Val");
		String[] price = request.getParameterValues("price");
		String[] nums = request.getParameterValues("nums");
		String[] productCode = request.getParameterValues("productCode");
		String cname = request.getParameter("cname");
		String marketPrice = request.getParameter("marketPrice");
		String realPrice = request.getParameter("realPrice");
		String categoryType = request.getParameter("categoryType");
		String editor1 = request.getParameter("editor1");

		String reJson = null;
		SysMessage msg = new SysMessage();

		Shop shop = (Shop) request.getSession().getAttribute(Constants.USER_SESSION);

		boolean c1 = (attr1 == null || attr1Val == null || attr2 == null || attr2Val == null || price == null || nums == null);
		if (c1) {
			msg.setCode("-1");
			msg.setMsg("参数错误");
			logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
			return reJson;
		}

		boolean c3 = attr1.length == 0 || attr1Val.length == 0 || attr2.length == 0 || attr2Val.length == 0
				|| price.length == 0 || nums.length == 0;

		boolean c2 = attr1.length != attr1Val.length || attr2.length != attr2Val.length
				|| (attr1.length * attr2.length != price.length) || (attr1.length * attr2.length != nums.length);

		if (c3 || c2) {
			msg.setCode("-2");
			msg.setMsg("参数数量不一致");
			logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
			return reJson;
		}

		Commodity commodity = new Commodity();
		List<CategoryPropertiesVal> cpList = new LinkedList<CategoryPropertiesVal>();
		List<Inventory> ilist = new LinkedList<Inventory>();

		for (int i = 0; i < attr1.length; i++) {
			CategoryPropertiesVal cp = new CategoryPropertiesVal();
			cp.setCpid(Utils.strToInteger(Utils.getArrayVal(i, attr1Val)));
			cp.setCpvalue(attr1[i]);
			cpList.add(cp);
		}
		for (int i = 0; i < attr2.length; i++) {
			CategoryPropertiesVal cp = new CategoryPropertiesVal();
			cp.setCpid(Utils.strToInteger(Utils.getArrayVal(i, attr2Val)));
			cp.setCpvalue(attr2[i]);
			cpList.add(cp);
		}

		int index = 0;

		for (int i = 0; i < attr1.length; i++) {
			for (int j = 0; j < attr2.length; j++) {
				Inventory inventory = new Inventory();
				inventory.setAttr1(Utils.strToInteger(Utils.getArrayVal(i, attr1Val)));
				inventory.setAttr2(Utils.strToInteger(Utils.getArrayVal(i, attr2Val)));
				inventory.setSid(shop.getId());
				inventory.setNums(Utils.strToInteger(Utils.getArrayVal(index, nums)));
				inventory.setPrice(Utils.strToDouble(Utils.getArrayVal(index, price)));
				inventory.setProductCode(Utils.getArrayVal(index, productCode));
				ilist.add(inventory);
				index++;

			}

		}

		commodity.setSid(shop.getId());
		commodity.setCreateTime(new Date());
		commodity.setName(cname);
		commodity.setRealPrice(Utils.strToDouble(realPrice));
		commodity.setMarketPrice(Utils.strToDouble(marketPrice));
		commodity.setType(Utils.strToInteger(categoryType));

		logger.info(cpList);
		logger.info(ilist);

		boolean flag = false;
		try {
			int id = inventoryService.insertInventory(commodity, ilist, cpList);
			flag = (id > 0) ? true : false;
		} catch (Exception e) {
			logger.error(this.getClass() + " addommodity error:" + e.getMessage());
			e.printStackTrace();
		}

		if (flag) {
			msg.setCode("1");
			msg.setMsg("添加成功");
		} else {
			msg.setCode("0");
			msg.setMsg("添加失败");
		}
		logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
		return reJson;
	}

	@RequestMapping(value = "/commodity/uploadImage")
	@ResponseBody
	public String uploadImage(HttpServletRequest request, Model model, @RequestParam MultipartFile file) {

		String reJson = null;
		SysMessage msg = new SysMessage();
		if (file == null || file.getSize() == 0) {
			msg.setCode("-1");
			msg.setMsg("文件长度为空");
			logger.info(this.getClass().getName() + (reJson = Utils.objToJson(msg)));
			return reJson;
		}

		System.out.println(file);

		String webPath = null;
		String path = request.getSession().getServletContext()
				.getRealPath(webPath = "/images/tmp/commodity/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date()));

		try {

			String type = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".") + 1);
			// file.getOriginalFilename()
			String fileName = java.util.UUID.randomUUID() + "." + type;
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path, fileName));
			msg.setCode("1");
			msg.setMsg("添加成功");
			msg.setToUrl(webPath+"/"+fileName);
		} catch (IOException e) {
			msg.setCode("-10");
			msg.setMsg("添加异常");
			e.printStackTrace();
		}

		logger.info(this.getClass().getName() + (reJson = Utils.objToJson(msg)));
		return reJson;
	}

	//
	// class CpdateCommodityImg implements Runnable{
	//
	//
	//
	// @Override
	// public void run() {
	//
	// }
	//
	// }

	@RequestMapping(value = "/commodity/list")
	public String list(HttpServletRequest request, Model model) {

		Shop shop = (Shop) request.getSession().getAttribute(Constants.USER_SESSION);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sid", shop.getId());
		List list = commodityService.getCommodityList(param);
		model.addAttribute("list", list);
		return "commodity/listCommodity";
	}
}
