package com.simland.backstage.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

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
import com.simland.core.base.ConstantsImage;
import com.simland.core.base.SysMessage;
import com.simland.core.base.Utils;
import com.simland.core.base.page.PageView;
import com.simland.core.module.purview.entity.ShopUser;
import com.simland.core.module.shop.entity.CategoryPropertiesVal;
import com.simland.core.module.shop.entity.Commodity;
import com.simland.core.module.shop.entity.CommodityDetails;
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

	String reJson = null;
	SysMessage msg = new SysMessage();

	@RequestMapping(value = "/commodity/addCommodityView")
	public String addCommodityShow(HttpServletRequest request, Model model) {

		setCategoryProperties(model);

		return "commodity/editCommodity";
	}

	@RequestMapping(value = "/commodity/editCommodityView")
	public ModelAndView editCommodityShow(HttpServletRequest request, Model model) {
		String cid = request.getParameter("cid");
		ShopUser sessionShop = (ShopUser) request.getSession().getAttribute(Constants.USER_SESSION);
		if (cid == null || Utils.strToInteger(cid) == 0) {
			return new ModelAndView("redirect:/commodity/list");
		}

		Commodity commodity = commodityService.getCommodity(Utils.strToInteger(cid), sessionShop.getSid());
		if (commodity == null) {
			return new ModelAndView("redirect:/commodity/list");
		}

		setCategoryProperties(model);

		model.addAttribute("c", commodity);

		return new ModelAndView("commodity/editCommodity");
	}

	private void setCategoryProperties(Model model) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pid", 0);
		List list = categoryPropertiesService.getCategoryPropertiesList(param);
		model.addAttribute("cplist", list);
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

		int cid = Utils.strToInteger(request.getParameter("cid"));// 商品ID

		String[] attr1 = request.getParameterValues(String.valueOf("attr1_" + categoryType));// 属性1值
		String[] attr1Val = request.getParameterValues("attr1Val_" + categoryType);// 属性1ID
		String[] attr2 = request.getParameterValues("attr2_" + categoryType);// 属性2值
		String[] attr2Val = request.getParameterValues("attr2Val_" + categoryType);// 属性2ID
		String[] price = request.getParameterValues("price_" + categoryType);// 价格
		String[] nums = request.getParameterValues("nums_" + categoryType);// 库存数量
		String[] productCode = request.getParameterValues("productCode_" + categoryType);// 商品编码
		String[] imageName = request.getParameterValues("imageName_" + categoryType);// 图片

		String[] iAttr1 = request.getParameterValues(String.valueOf("iAttr1_" + categoryType));
		String[] iAttr1Val = request.getParameterValues(String.valueOf("iAttr1Val_" + categoryType));
		String[] iAttr2 = request.getParameterValues(String.valueOf("iAttr2_" + categoryType));
		String[] iAttr2Val = request.getParameterValues(String.valueOf("iAttr2Val_" + categoryType));

		String cname = request.getParameter("cname");// 商品名称
		String marketPrice = request.getParameter("marketPrice");// 市场价格
		String realPrice = request.getParameter("realPrice");// 真实价格
		String editor1 = request.getParameter("editor1");// 图文编辑
		String isNew = request.getParameter("isNew");// 是否新品
		String isSpecial = request.getParameter("isSpecial");// 特价
		String isVip = request.getParameter("isVip");// 是否VIP

		ShopUser shopUser = (ShopUser) request.getSession().getAttribute(Constants.USER_SESSION);

		boolean c1 = (price == null || nums == null || productCode == null || imageName == null);
		if (c1) {
			msg.setCode("-2");
			msg.setMsg("参数错误");
			logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
			return reJson;
		}

		ReentrantLock reentrantLock = new ReentrantLock();
		reentrantLock.lock();

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
			inventory.setCpid(Utils.strToInteger(categoryType));
			inventory.setAttr1(Utils.strToInteger(Utils.getArrayVal(i, iAttr1Val)));
			inventory.setAttr2(Utils.strToInteger(Utils.getArrayVal(i, iAttr2Val)));
			inventory.setSid(shopUser.getId());
			inventory.setNums(Utils.strToInteger(Utils.getArrayVal(i, nums)));
			inventory.setPrice(Utils.strToDouble(Utils.getArrayVal(i, price)));
			inventory.setProductCode(Utils.getArrayVal(index, productCode));
			inventory.setImage(ConstantsImage.copyFile(request, ConstantsImage.COMMODITY,
					Utils.getArrayVal(i, imageName)));
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
		CommodityDetails commodityDetails = new CommodityDetails();
		commodityDetails.setInfo(editor1);
		commodity.setCommodityDetails(commodityDetails);
		if (ilist.size() > 0) {
			commodity.setImg(ilist.get(0).getImage());
		}

		logger.info(cpList);
		logger.info(ilist);

		int id = 0;
		try {
			if (cid > 0) {
				commodity.setId(cid);
				id = inventoryService.updateInventory(commodity, ilist, cpList, msg);
			} else {
				id = inventoryService.insertInventory(commodity, ilist, cpList, msg);
			}
		} catch (Exception e) {
			reentrantLock.unlock();
			logger.error(this.getClass() + " addommodity error:" + e.getMessage());
			e.printStackTrace();
		}

		reentrantLock.unlock();

		if (id > 0) {
			msg.setCode("1");
			msg.setMsg("保存成功");
		} else {
			msg.setCode("0");
			msg.setMsg("保存失败");
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

		ReentrantLock reentrantLock = new ReentrantLock();
		reentrantLock.lock();

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
		} finally {
			reentrantLock.unlock();
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

	/***
	 * 发布商品
	 * 
	 * @return
	 */
	@RequestMapping(value = "/commodity/issueCommodityList")
	public ModelAndView issueCommodityList(HttpServletRequest request, Model model, PageView pageView) {
		ShopUser shopUser = (ShopUser) request.getSession().getAttribute(Constants.USER_SESSION);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sid", shopUser.getId());

		int totalRecord = commodityService.getCommodityCount(param);
		if (totalRecord == 0) {
			return new ModelAndView("commodity/issueCommodityList");
		}

		pageView.setPageSize(15);

		param.put("endSize", pageView.getFirstResult());
		param.put("pageSize", pageView.getPageSize());
		param.put("sortColumns", "id");

		List list = commodityService.getSplitCommodityList(param);

		pageView.setTotalRecord(totalRecord);
		pageView.setRecords(list);

		model.addAttribute("pageView", pageView);
		return new ModelAndView("commodity/issueCommodityList");
	}

	/***
	 * 发布商品
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/commodity/issueCommodity")
	public ModelAndView issueCommodity(HttpServletRequest request, Model model, PageView pageView) {

		String[] ids = request.getParameterValues("ids");
		String issueType = request.getParameter("issueType");
		if (Utils.isObjectEmpty(ids) || ids.length == 0 || Utils.isObjectEmpty(issueType)) {
			return new ModelAndView("redirect:/commodity/issueCommodityList");
		}

		ReentrantLock reentrantLock = new ReentrantLock();
		reentrantLock.lock();

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ids", Utils.toIntegerArray(ids));
		if (Utils.strToInteger(issueType) == 1) {// 上架
			param.put("status", Commodity.status_1);
		} else if (Utils.strToInteger(issueType) == 2) {// 下架
			param.put("status", Commodity.status_2);
		}
		commodityService.updateCommodityStatusByIds(param);
		reentrantLock.unlock();

		return new ModelAndView("redirect:/commodity/issueCommodityList?currentPage="+pageView.getCurrentPage());
	}
}
