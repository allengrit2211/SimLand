package com.simland.appservice.controller;

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

import com.simland.appservice.controller.security.SessionManager;
import com.simland.core.base.Utils;
import com.simland.core.base.page.PageView;
import com.simland.core.module.chat.service.IMessageService;

@Controller
public class MessageController {

	public static final Log logger = LogFactory.getLog(MessageController.class);

	@Autowired
	private IMessageService messageService;

	@RequestMapping(value = "/buy/chatList")
	public String list(HttpServletRequest request, Model model) {

		String sid = request.getParameter("sid");
		int uid = SessionManager.getUser().getId();
		String currentPage = request.getParameter("icurrentPage");
		if (Utils.isObjectEmpty(sid) || Utils.isObjectEmpty(uid) || Utils.isObjectEmpty(currentPage)) {
			return "buy/chatMessageAjax";
		}

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sid", Utils.strToInteger(sid));
		param.put("uid", uid);
		int totalRecord = messageService.getMessageCount(param);

		PageView pageView = new PageView();
		pageView.setCurrentPage(Utils.strToInteger(currentPage));
		pageView.setPageSize(20);
		pageView.setTotalRecord(totalRecord);
		param.put("endSize", pageView.getFirstResult());
		param.put("pageSize", pageView.getPageSize());

		List list = messageService.getSplitMessageList(param);
		model.addAttribute("uid", uid);
		model.addAttribute("list", list);

		return "buy/chatMessageAjax";
	}

}
