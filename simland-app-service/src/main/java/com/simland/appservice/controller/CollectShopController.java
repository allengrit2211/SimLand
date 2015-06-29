package com.simland.appservice.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
import com.simland.core.base.page.PageView;
import com.simland.core.module.user.entity.CollectShop;
import com.simland.core.module.user.entity.User;
import com.simland.core.module.user.service.ICollectShopService;

@Controller
public class CollectShopController {

	public static final Log logger = LogFactory.getLog(CollectShopController.class);

	@Autowired
	private ICollectShopService collectShopService;

	/***
	 * 登录检测
	 * 
	 * @return
	 */
	private User loginChk(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute(Constants.USER_SESSION);
		if (Utils.isObjectEmpty(obj)) {
			return null;
		} else {
			return (User) obj;
		}
	}

	/***
	 * 收藏店铺
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user/collectShop")
	@ResponseBody
	public String collectShop(HttpServletRequest request, Model model) {
		String reJson = null;
		SysMessage msg = new SysMessage();
		User user = null;
		if (Utils.isObjectEmpty(user = loginChk(request))) {
			msg.setCode("-1");
			msg.setMsg("请先登录");
			logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
			return reJson;
		}

		String sid = request.getParameter("sid");
		if (Utils.isObjectEmpty(sid)) {
			msg.setCode("-2");
			msg.setMsg("参数错误");
			logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
			return reJson;
		}

		Lock lock = new ReentrantLock();
		try {
			lock.lock();
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("uid", user.getId());
			param.put("sid", Utils.strToInteger(sid));
			int count = collectShopService.getCollectShopCount(param);
			if (count > 0) {
				msg.setCode("-3");
				msg.setMsg("已收藏");
				logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
				param = null;
				return reJson;
			}

			CollectShop collectShop = new CollectShop();
			collectShop.setCreateTime(new Date());
			collectShop.setSid(Utils.strToInteger(sid));
			collectShop.setUid(user.getId());
			int id = collectShopService.insertCollectShop(collectShop);
			if (id > 0) {
				msg.setCode("1");
				msg.setMsg("收藏成功");
			} else {
				msg.setCode("-4");
				msg.setMsg("收藏失败");
			}

			logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));

			msg = null;
			collectShop = null;
			param = null;
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " collectShop error :" + e.getMessage());
		} finally {
			lock.unlock();
		}

		return reJson;
	}

	/***
	 * 店铺收藏显示
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user/collectShopShow")
	@ResponseBody
	public String collectShopShow(HttpServletRequest request, Model model) {

		Map<String, Object> json = new HashMap<String, Object>();
		String reJson = null;

		User user = null;
		if (Utils.isObjectEmpty(user = loginChk(request))) {
			json.put("code", "-1");
			json.put("msg", "请先登录");
			logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(json, request.getParameter("callback"))));
			return reJson;
		}

		int currentPage = Utils.strToInteger(request.getParameter("icurrentPage"));

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("uid", user.getId());

		int totalRecord = collectShopService.getCollectShopCount(param);
		PageView pageView = new PageView();
		pageView.setCurrentPage(currentPage);
		pageView.setPageSize(20);
		pageView.setTotalRecord(totalRecord);
		param.put("endSize", pageView.getFirstResult());
		param.put("pageSize", pageView.getPageSize());

		List<CollectShop> list = collectShopService.getSplitCollectShopList(param);

		json.put("code", "1");
		json.put("totalPage", pageView.getTotalPage());
		json.put("list", list);

		logger.info(reJson = Utils.objToJsonp(json, request.getParameter("callback")));

		param.clear();
		list.clear();

		return reJson;
	}
}
