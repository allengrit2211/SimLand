package com.simland.appservice.controller;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simland.appservice.controller.security.SessionManager;
import com.simland.core.base.SysMessage;
import com.simland.core.base.SystemConstants;
import com.simland.core.base.Utils;
import com.simland.core.module.order.entity.Cart;
import com.simland.core.module.order.entity.CartItem;
import com.simland.core.module.order.service.IOrderService;
import com.simland.core.module.shop.entity.Commodity;
import com.simland.core.module.shop.entity.Shop;
import com.simland.core.module.shop.service.ICommodityService;
import com.simland.core.module.shop.service.IShopService;
import com.simland.core.module.user.entity.User;

@Controller
public class CartController {

	public static final Log logger = LogFactory.getLog(CartController.class);

	@Autowired
	@Qualifier("simpleOrderService")
	private IOrderService simpleOrderService;

	@Autowired
	private ICommodityService commodityService;

	@Autowired
	private IShopService shopService;

	@RequestMapping(value = "/buy/cart")
	public String showShoppingCart(HttpServletRequest request, Model model) {

		//User user = SessionManager.getUser();
		//Cart cart = user.getCart();
		//model.addAttribute("cartItmes", cart.getCartItems());
		return "buy/cart";
	}

	/***
	 * 添加购物车
	 * 
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/buy/addCart")
	public String addCart(HttpServletRequest request, Model model) {

		String reJson = null;
		SysMessage msg = new SysMessage();

		String cid = request.getParameter("cid");
		String attr1Val = request.getParameter("attr1Val");
		String attr2Val = request.getParameter("attr2Val");
		String buyNum = request.getParameter("buyNum");

		Commodity c = commodityService.getCommodity(Utils.strToInteger(cid));
		if (Utils.isObjectEmpty(c)) {
			msg.setCode("-1");
			msg.setMsg("参数错误");
			logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
			return reJson;
		}

		// 设置属性
		c.setAttr1(SystemConstants.categoryPropertiesMap.get(attr1Val));
		c.setAttr2(SystemConstants.categoryPropertiesMap.get(attr2Val));
		if (c.getAttr1() == null || c.getAttr2() == null) {
			msg.setCode("-2");
			msg.setMsg("请选择商品属性");
			logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
			return reJson;
		}

		Shop shop = shopService.getShop(c.getSid());
		if (Utils.isObjectEmpty(shop)) {
			msg.setCode("-3");
			msg.setMsg("店铺不存在");
			logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
			return reJson;
		}

		// Builder builder = null;
		// Director director = new Director(builder = new SimpleBuilder(user,
		// Utils.strToInteger(buyNum), c));
		// director.construct();
		// Vector<Order> olist = new Vector<Order>();
		// olist.add(builder.getResult());

		User user = SessionManager.getUser();

		user.setCart(Cart.addCart(user.getCart(), shop, c, Utils.strToInteger(buyNum)));

		SessionManager.setUser(user, request);

		msg.setCode("1");
		msg.setMsg("添加成功");
		logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));

		return reJson;

	}
}
