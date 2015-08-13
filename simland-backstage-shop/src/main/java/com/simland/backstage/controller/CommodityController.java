package com.simland.backstage.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import org.springframework.web.servlet.ModelAndView;

import com.simland.core.base.Constants;
import com.simland.core.base.SysMessage;
import com.simland.core.base.Utils;
import com.simland.core.base.page.PageView;
import com.simland.core.module.purview.entity.ShopUser;
import com.simland.core.module.shop.entity.CategoryPropertiesVal;
import com.simland.core.module.shop.entity.Commodity;
import com.simland.core.module.shop.entity.Inventory;
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

	public static final String IMAGE_URL = "/simland-app-service";

	String reJson = null;
	SysMessage msg = new SysMessage();

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

		String categoryType = request.getParameter("categoryType");
		if (Utils.isObjectEmpty(categoryType) || Utils.strToInteger(categoryType) <= 0) {
			msg.setCode("-1");
			msg.setMsg("参数错误");
			logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
			return reJson;
		}

		String[] attr1 = request.getParameterValues(String.valueOf("attr1_" + categoryType));
		String[] attr1Val = request.getParameterValues("attr1Val_" + categoryType);
		String[] attr2 = request.getParameterValues("attr2_" + categoryType);
		String[] attr2Val = request.getParameterValues("attr2Val_" + categoryType);
		String[] price = request.getParameterValues("price_" + categoryType);
		String[] nums = request.getParameterValues("nums_" + categoryType);
		String[] productCode = request.getParameterValues("productCode_" + categoryType);
		String[] imageName = request.getParameterValues("imageName_" + categoryType);

		String[] iAttr1 = request.getParameterValues(String.valueOf("iAttr1_" + categoryType));
		String[] iAttr1Val = request.getParameterValues(String.valueOf("iAttr1Val_" + categoryType));
		String[] iAttr2 = request.getParameterValues(String.valueOf("iAttr2_" + categoryType));
		String[] iAttr2Val = request.getParameterValues(String.valueOf("iAttr2Val_" + categoryType));

		String cname = request.getParameter("cname");
		String marketPrice = request.getParameter("marketPrice");
		String realPrice = request.getParameter("realPrice");
		String editor1 = request.getParameter("editor1");
		String isNew = request.getParameter("isNew");
		String isSpecial = request.getParameter("isSpecial");
		String isVip = request.getParameter("isVip");
		;

		ShopUser shopUser = (ShopUser) request.getSession().getAttribute(Constants.USER_SESSION);

		boolean c1 = (price == null || nums == null || productCode == null || imageName == null);
		if (c1) {
			msg.setCode("-2");
			msg.setMsg("参数错误");
			logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
			return reJson;
		}

		Commodity commodity = new Commodity();
		List<CategoryPropertiesVal> cpList = new LinkedList<CategoryPropertiesVal>();
		List<Inventory> ilist = new LinkedList<Inventory>();

		for (int i = 0; attr1 != null && i < attr1.length; i++) {

			String attr1Str = Utils.getArrayVal(i, attr1);
			if (Utils.isObjectEmpty(attr1Str))
				continue;

			CategoryPropertiesVal cp = new CategoryPropertiesVal();
			cp.setCpid(Utils.strToInteger(Utils.getArrayVal(i, attr1Val)));
			cp.setCpvalue(attr1Str);
			cpList.add(cp);
		}
		for (int i = 0; attr2 != null && i < attr2.length; i++) {

			String attr2Str = Utils.getArrayVal(i, attr2);
			if (Utils.isObjectEmpty(attr2Str))
				continue;

			CategoryPropertiesVal cp = new CategoryPropertiesVal();
			cp.setCpid(Utils.strToInteger(Utils.getArrayVal(i, attr2Val)));
			cp.setCpvalue(attr2Str);
			cpList.add(cp);
		}

		int index = 0;

		for (int i = 0; iAttr1 != null && i < iAttr1.length; i++) {
			Inventory inventory = new Inventory();
			inventory.setAttr1(Utils.strToInteger(Utils.getArrayVal(i, iAttr1Val)));
			inventory.setAttr2(Utils.strToInteger(Utils.getArrayVal(i, iAttr2Val)));
			inventory.setSid(shopUser.getId());
			inventory.setNums(Utils.strToInteger(Utils.getArrayVal(i, nums)));
			inventory.setPrice(Utils.strToDouble(Utils.getArrayVal(i, price)));
			inventory.setProductCode(Utils.getArrayVal(index, productCode));
			inventory.setImage(copyFile(request, Utils.getArrayVal(i, imageName)));
			ilist.add(inventory);
			index++;
		}

		commodity.setSid(shopUser.getId());
		commodity.setCreateTime(new Date());
		commodity.setName(cname);
		commodity.setRealPrice(Utils.strToDouble(realPrice));
		commodity.setMarketPrice(Utils.strToDouble(marketPrice));
		commodity.setType(Utils.strToInteger(categoryType));
		commodity.setIsNew(Utils.strToInteger(isNew));
		commodity.setIsSpecial(Utils.strToInteger(isSpecial));
		commodity.setIsVip(Utils.strToInteger(isVip));
		if (ilist.size() > 0) {
			commodity.setImg(ilist.get(0).getImage());
		}

		logger.info(cpList);
		logger.info(ilist);

		boolean flag = false;
		try {
			int id = inventoryService.insertInventory(commodity, ilist, cpList, msg);
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
		if (file == null || file.getSize() == 0) {
			msg.setCode("-1");
			msg.setMsg("文件长度为空");
			logger.info(this.getClass().getName() + (reJson = Utils.objToJson(msg)));
			return reJson;
		}

		System.out.println(file);

		String webPath = null;
		String path = request
				.getSession()
				.getServletContext()
				.getRealPath(webPath = "/images/tmp/commodity/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date()));

		try {

			String type = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".") + 1);
			// file.getOriginalFilename()
			String fileName = java.util.UUID.randomUUID() + "." + type;
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path, fileName));
			msg.setCode("1");
			msg.setMsg("添加成功");
			msg.setToUrl(webPath + "/" + fileName);
		} catch (IOException e) {
			msg.setCode("-10");
			msg.setMsg("添加异常");
			e.printStackTrace();
		}

		logger.info(this.getClass().getName() + (reJson = Utils.objToJson(msg)));
		return reJson;
	}

	/***
	 * 复制商品图到新目录
	 * 
	 * @param srcFile
	 *            源文件目录
	 * @param newFile
	 *            新文件目录
	 * @return
	 */
	private String copyFile(HttpServletRequest request, String srcFile) {
		try {

			String path = request.getSession().getServletContext().getRealPath("/") + srcFile;
			path = path.replaceAll("\\\\", "/");

			String webPath = null;
			String newFile = request
					.getSession()
					.getServletContext()
					.getRealPath(webPath = "/images/commodity/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date()));

			newFile = newFile.replaceAll("\\\\", "/");
			newFile = newFile.replaceAll(request.getSession().getServletContext().getContextPath(), IMAGE_URL);

			InputStream is = new FileInputStream(new File(path));

			String type = srcFile.substring(srcFile.lastIndexOf(".") + 1);
			// file.getOriginalFilename()
			String fileName = java.util.UUID.randomUUID() + "." + type;

			FileUtils.copyInputStreamToFile(is, new File(newFile, fileName));

			return webPath +File.separator+ fileName;
		} catch (Exception e) {
			logger.error("copyFile error:" + e.getMessage());
			return "";
		}
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
	public ModelAndView list(HttpServletRequest request, Model model, PageView pageView) {

		ShopUser shopUser = (ShopUser) request.getSession().getAttribute(Constants.USER_SESSION);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sid", shopUser.getId());

		int totalRecord = commodityService.getCommodityCount(param);
		if (totalRecord == 0) {
			model.addAttribute("msg", "无数据");
			return new ModelAndView("commodity/listCommodity");
		}

		pageView.setPageSize(10);

		param.put("endSize", pageView.getFirstResult());
		param.put("pageSize", pageView.getPageSize());
		param.put("sortColumns", "id");

		List list = commodityService.getSplitCommodityList(param);

		pageView.setTotalRecord(totalRecord);
		pageView.setRecords(list);

		model.addAttribute("pageView", pageView);
		return new ModelAndView("commodity/listCommodity");
	}

	@RequestMapping(value = "/commodity/popupList")
	public ModelAndView popupList(HttpServletRequest request, Model model, PageView pageView) {

		ShopUser shopUser = (ShopUser) request.getSession().getAttribute(Constants.USER_SESSION);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sid", shopUser.getId());

		int totalRecord = commodityService.getCommodityCount(param);
		if (totalRecord == 0) {
			return new ModelAndView("commodity/listCommodityPopup");
		}

		pageView.setPageSize(5);

		param.put("endSize", pageView.getFirstResult());
		param.put("pageSize", pageView.getPageSize());
		param.put("sortColumns", "id");

		List list = commodityService.getSplitCommodityList(param);

		pageView.setTotalRecord(totalRecord);
		pageView.setRecords(list);

		model.addAttribute("pageView", pageView);
		return new ModelAndView("commodity/listCommodityPopup");
	}
}
