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
import org.springframework.web.servlet.ModelAndView;

import com.simland.backstage.util.Constants;
import com.simland.core.base.page.PageView;
import com.simland.core.module.shop.entity.Shop;
import com.simland.core.module.shop.entity.WaitOrder;
import com.simland.core.module.shop.service.IWaitOrderService;

@Controller
public class WaitOrderController {

	public static final Log logger = LogFactory.getLog(WaitOrderController.class);

	@Autowired
	private IWaitOrderService waitOrderService;

	/***
	 * 待下订单列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/shop/waitOrderList")
	public ModelAndView list(HttpServletRequest request, Model model, PageView pageView) {

		Shop shop = (Shop) request.getSession().getAttribute(Constants.USER_SESSION);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sid", shop.getId());
		param.put("isDel", WaitOrder.ISDEL_0);

		int totalRecord = waitOrderService.getWaitOrderCount(param);
		if (totalRecord == 0) {
			return new ModelAndView("shop/waitOrderList");
		}

		pageView.setPageSize(10);

		param.put("endSize", pageView.getFirstResult());
		param.put("pageSize", pageView.getPageSize());
		param.put("sortColumns", "id");

		List list = waitOrderService.getSplitWaitOrderList(param);

		pageView.setTotalRecord(totalRecord);
		pageView.setRecords(list);

		model.addAttribute("pageView", pageView);
		return new ModelAndView("shop/waitOrderList");

	}

}
