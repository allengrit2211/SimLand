package com.simland.appservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.simland.appservice.controller.security.SessionManager;
import com.simland.core.base.Constants;
import com.simland.core.base.SysMessage;
import com.simland.core.base.Utils;
import com.simland.core.base.page.PageView;
import com.simland.core.module.user.entity.Address;
import com.simland.core.module.user.entity.User;
import com.simland.core.module.user.service.IAddressService;
import com.simland.core.module.user.service.ICollectShopService;
import com.simland.core.module.user.service.IUserService;

@Controller
public class UserController {

	public static final Log logger = LogFactory.getLog(UserController.class);

	@Autowired
	private IUserService userService;

	@Autowired
	private ICollectShopService collectShopService;

	@Autowired
	private IAddressService addressService;

	/****
	 * 登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loginPage")
	public String login(HttpServletRequest request) {

		System.out.println(request.getQueryString());

		String reJson = null;
		String ajax = request.getParameter("ajax");
		if ("ajax".equalsIgnoreCase(ajax)) {
			SysMessage msg = new SysMessage();
			logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
			msg = null;
			return reJson;
		} else {
			return "login";
		}

	}

	/**
	 * 退出
	 * 
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String logout() {
		logger.debug("logout");
		return "logout";
	}

	/***
	 * 用户中心
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user/userCenter")
	public String userCenter(HttpServletRequest request, Model model) {

		User user = SessionManager.getUser();
		User user1 = userService.getUser(user.getId());
		model.addAttribute("user", user1);
		return "user/userCenter";
	}

	/***
	 * 消息中心
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user/messageCenter")
	public String messageCenter() {
		return "user/messageCenter";
	}

	@RequestMapping(value = "/loginAjax")
	@ResponseBody
	public String loginAjax(HttpServletRequest request, Model model) {
		SysMessage msg = new SysMessage();
		String uname = request.getParameter("uname");
		String upw = request.getParameter("upw");

		String reJson = null;
		if (Utils.isObjectEmpty(uname) || Utils.isObjectEmpty(upw)) {
			msg.setCode("-1");
			msg.setMsg("用户名或密码不能为空");
			logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
			return reJson;
		}

		// User user = userService.login(uname, upw, msg);
		// if (Utils.isObjectNotEmpty(user)) {
		// request.getSession().setAttribute(Constants.USER_SESSION, user);
		// }

		// spring security 将权限及用户信息存入securityContext
		System.out.println("start ");
		UserDetails userDetails = userService.loadUserByUsername(uname);
		if (Utils.isObjectEmpty(userDetails)) {
			msg.setCode("-2");
			msg.setMsg("用户名或密码不存在");
			logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
			return reJson;
		}

		System.out.println("userDetails end" + userDetails);
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities()));
		HttpSession session = request.getSession(true);
		session.setAttribute(Constants.SPRING_SECURITY_CONTEXT, securityContext);

		msg.setCode("1");
		msg.setMsg("登录成功");
		logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
		return reJson;
	}

	@RequestMapping(value = "/logindo")
	public String logindo(HttpServletRequest request, Model model) {

		SysMessage msg = new SysMessage();
		String uname = request.getParameter("uname");
		String upw = request.getParameter("upw");
		String toUrl = request.getParameter("toUrl");

		if (Utils.isObjectEmpty(uname) || Utils.isObjectEmpty(upw)) {
			msg.setCode("-1");
			msg.setMsg("用户名或密码不能为空");
			return "login";
		}

		// User user = userService.login(uname, upw, msg);
		// if (Utils.isObjectNotEmpty(user)) {
		// request.getSession().setAttribute(Constants.USER_SESSION, user);
		// }

		// spring security 将权限及用户信息存入securityContext
		System.out.println("start ");
		UserDetails userDetails = userService.loadUserByUsername(uname);
		if (Utils.isObjectEmpty(userDetails)) {
			msg.setCode("-2");
			msg.setMsg("用户名或密码不存在");
			return "login";
		}

		System.out.println("userDetails end" + userDetails);
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities()));
		HttpSession session = request.getSession(true);
		session.setAttribute(Constants.SPRING_SECURITY_CONTEXT, securityContext);

		System.out.println("-----------------" + "redirect:" + toUrl);
		if (Utils.isObjectNotEmpty(toUrl)) {
			return "redirect:" + toUrl;
		}

		return "index";
	}

	/***
	 * 是否登录
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/isLogin")
	@ResponseBody
	public String isLogin(HttpServletRequest request, Model model) {
		String reJson = null;

		SysMessage msg = new SysMessage();
		Object obj = request.getSession().getAttribute(Constants.SPRING_SECURITY_CONTEXT);
		if (Utils.isObjectNotEmpty(obj)) {
			msg.setCode("1");
			msg.setMsg("已登录");
		} else {
			msg.setCode("2");
			msg.setMsg("未登录");
		}

		logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));

		return reJson;
	}

	/***
	 * 添加用户地址显示
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user/addAddressShow")
	public String addAddressShow(HttpServletRequest request, Model model) {
		model.addAttribute("toUrl", request.getParameter("toUrl"));

		String id = request.getParameter("id");
		User user = SessionManager.getUser();
		if (id != null) {
			Address adderss = addressService.getAddress(Utils.strToInteger(id), user.getId());
			model.addAttribute("address", adderss);
		}

		return "user/addAddress";
	}

	/***
	 * 添加地址
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user/addAddress")
	public ModelAndView addAddress(HttpServletRequest request, Model model) {

		String id = request.getParameter("id");

		Address address = new Address();
		address.setIsDefault(1);
		address.setUid(SessionManager.getUser().getId());
		address.setReceiverName(request.getParameter("receiverName"));
		address.setReceiverPhone(request.getParameter("receiverPhone"));
		address.setReceiverCity(request.getParameter("receiverCity"));
		address.setReceiverAddress(request.getParameter("receiverAddress"));
		address.setReceiverZipCode(request.getParameter("receiverZipCode"));

		if (Utils.isObjectNotEmpty(id) && Utils.strToInteger(id) != 0) {
			address.setId(Utils.strToInteger(id));
			address.setIsDefault(0);
			addressService.updateAddress(address);
		} else {
			addressService.insertAddress(address);
		}

		String toUrl = request.getParameter("toUrl");
		if (Utils.isObjectNotEmpty(Utils.notNullTrim(toUrl))) {
			return new ModelAndView("redirect:" + toUrl);
		} else
			return new ModelAndView("order/confirmOrder");
	}

	/***
	 * 用户地址列表
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user/listAddress")
	public ModelAndView listAddress(HttpServletRequest request, Model model) {

		int currentPage = Utils.strToInteger(request.getParameter("icurrentPage"));

		User user = SessionManager.getUser();

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("uid", user.getId());
		param.put("pageSize", 5);
		param.put("endSize", 10);

		int totalRecord = addressService.getAddressCount(param);

		PageView pageView = new PageView();
		pageView.setCurrentPage(currentPage);
		pageView.setPageSize(5);
		pageView.setTotalRecord(totalRecord);
		param.put("endSize", pageView.getFirstResult());
		param.put("pageSize", pageView.getPageSize());
		param.put("sortColumns", "id");

		List<Address> list = addressService.getSplitAddressList(param);
		model.addAttribute("list", list);

		return new ModelAndView("user/listAddress");

	}

	/***
	 * 设置用户默认地址
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user/defualtAddress")
	@ResponseBody
	public String defualtAddress(HttpServletRequest request, Model model) {

		String reJson = null;

		SysMessage msg = new SysMessage();
		User user = SessionManager.getUser();
		String id = request.getParameter("id");
		if (Utils.isObjectEmpty(id) || Utils.strToInteger(id) == 0) {
			msg.setCode("-1");
			msg.setMsg("参数错误");
			logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
			return reJson;
		}

		addressService.setUserDefaultAddress(user.getId(), Utils.strToInteger(id));
		msg.setCode("1");
		msg.setMsg("设置成功");
		logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
		return reJson;

	}
}
