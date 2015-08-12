package com.simland.backstage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.simland.core.base.Constants;
import com.simland.core.base.SysMessage;
import com.simland.core.base.Utils;
import com.simland.core.base.page.PageView;
import com.simland.core.module.order.entity.Cart;
import com.simland.core.module.order.entity.CartItem;
import com.simland.core.module.order.entity.Order;
import com.simland.core.module.order.service.IOrderService;
import com.simland.core.module.order.service.IOrderState;
import com.simland.core.module.purview.entity.ShopUser;
import com.simland.core.module.shop.entity.Commodity;
import com.simland.core.module.shop.entity.Shop;
import com.simland.core.module.shop.entity.WaitOrder;
import com.simland.core.module.shop.service.ICommodityService;
import com.simland.core.module.shop.service.IShopService;
import com.simland.core.module.shop.service.IWaitOrderService;
import com.simland.core.module.user.entity.Address;
import com.simland.core.module.user.entity.User;
import com.simland.core.module.user.service.IUserService;

@Controller
public class OrderController {

	public static final Log logger = LogFactory.getLog(OrderController.class);

	@Autowired
	private IOrderService orderService;

	@Autowired
	private IWaitOrderService waitOrderService;

	@Autowired
	private ICommodityService commodityService;

	@Autowired
	private IShopService shopService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IOrderState generalOrder;

	/***
	 * 进入创建订单页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/shop/createOrderShow")
	public String createOrderShow(HttpServletRequest request, Model model) {

		String id = request.getParameter("id");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		WaitOrder waitOrder = waitOrderService.getWaitOrder(param);

		if (Utils.isObjectEmpty(waitOrder)) {
			model.addAttribute("msg", "用户请求ID不存在，不能下单");
			return "order/createOrder";
		}

		model.addAttribute("waitOrder", waitOrder);
		return "order/createOrder";
	}

	/***
	 * 添加购物车
	 * 
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/shop/addCart")
	public String addCart(HttpServletRequest request, Model model) {

		String reJson = null;
		SysMessage msg = new SysMessage();

		String cid = request.getParameter("cid");
		String uid = request.getParameter("uid");
		// String attr1 = request.getParameter("attr1");
		// String attr2 = request.getParameter("attr2");
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
		// c.setAttr1(SystemConstants.categoryPropertiesMap.get(attr1));
		// c.setAttr2(SystemConstants.categoryPropertiesMap.get(attr2));
		c.setAttr1Val(attr1Val);
		c.setAttr2Val(attr2Val);
		// if (c.getAttr1() == null || c.getAttr2() == null) {
		// msg.setCode("-2");
		// msg.setMsg("请选择商品属性");
		// logger.info(this.getClass().getName() + (reJson =
		// Utils.objToJsonp(msg, request.getParameter("callback"))));
		// return reJson;
		// }

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

		User user = userService.getUser(Utils.strToInteger(uid));
		if (Utils.isObjectEmpty(user)) {
			msg.setCode("-4");
			msg.setMsg("用户不存在");
			logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
			return reJson;
		}

		ShopUser sessionShop = (ShopUser) request.getSession().getAttribute(Constants.USER_SESSION);

		sessionShop.setCart(Cart.addCart(sessionShop.getCart(), shop, c, Utils.strToInteger(buyNum)));
		request.getSession().setAttribute(Constants.USER_SESSION, sessionShop);

		msg.setCode("1");
		msg.setMsg("添加成功");
		msg.setText(Utils.arrayToJson(sessionShop.getCart().getCartItems().get(sessionShop)));
		logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));

		return reJson;

	}

	/***
	 * 删除购物车商品
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shop/delCart")
	public String delCart(HttpServletRequest request, Model model) {
		String skus = request.getParameter("skus");
		String reJson = null;
		SysMessage msg = new SysMessage();

		if (Utils.isObjectEmpty(skus) || skus.split(",").length == 0) {
			msg.setCode("-1");
			msg.setMsg("删除失败");
			logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
			return reJson;
		}

		ShopUser sessionShop = (ShopUser) request.getSession().getAttribute(Constants.USER_SESSION);

		Cart.delCart(sessionShop.getCart(), skus.split(","));

		msg.setCode("1");
		msg.setMsg("删除成功");
		logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));

		return reJson;

	}

	/***
	 * 购物车显示
	 * 
	 * @param request
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/shop/viewCartAjax")
	public String viewCart(HttpServletRequest request, Model model) {
		ShopUser shopUser = (ShopUser) request.getSession().getAttribute(Constants.USER_SESSION);

		Vector<CartItem> v = null;
		try {
			
			for (Entry<Shop, Vector<CartItem>> cartItem : shopUser.getCart().getCartItems().entrySet()) {
				v = cartItem.getValue();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("clist", v);
		return "order/viewCartAjax";
	}

	/***
	 * 提交订单
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shop/submitOrder")
	public String submitOrder(HttpServletRequest request, Model model) {

		String reJson = null;
		SysMessage msg = new SysMessage();

		String uid = request.getParameter("uid");
		String wid = request.getParameter("wid");

		ShopUser sessionShop = (ShopUser) request.getSession().getAttribute(Constants.USER_SESSION);
		Cart cart = sessionShop.getCart();

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", Utils.strToInteger(wid));
		WaitOrder waitOrder = waitOrderService.getWaitOrder(param);
		if (Utils.isObjectEmpty(waitOrder)) {
			msg.setCode("-2");
			msg.setMsg("用户未请求下单");
			logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
			return reJson;
		}

		User user = userService.getUser(Utils.strToInteger(uid));
		if (Utils.isObjectEmpty(user)) {
			msg.setCode("-4");
			msg.setMsg("用户不存在");
			logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));
			return reJson;
		}

		Address userAddress = new Address();
		userAddress.setId(0);
		userAddress.setReceiverProvince(request.getParameter("receiverProvince"));
		userAddress.setReceiverCity(request.getParameter("receiverCity"));
		userAddress.setReceiverDistrict(request.getParameter("receiverDistrict"));
		userAddress.setReceiverAddress(request.getParameter("receiverAddress"));
		userAddress.setReceiverName(request.getParameter("receiverName"));
		userAddress.setReceiverPhone(request.getParameter("receiverPhone"));

		String remark = request.getParameter("remark");

		// 选择所有添加的商品
		Cart.addSettlementItems(cart, new String[] {});

		int id = generalOrder.create(user, userAddress, cart, msg, remark);

		if (id > 0) {

			sessionShop.setCart(null);
			request.getSession().setAttribute(Constants.USER_SESSION, sessionShop);

			WaitOrder waitOrderUp = new WaitOrder();
			waitOrderUp.setId(waitOrder.getId());
			waitOrderUp.setIsDel(WaitOrder.ISDEL_1);
			waitOrderService.updateWaitOrder(waitOrderUp);

			msg.setCode("1");
			msg.setMsg("订单创建成功");
		}

		logger.info(this.getClass().getName() + (reJson = Utils.objToJsonp(msg, request.getParameter("callback"))));

		return reJson;
	}

	/***
	 * 订单列表
	 * 
	 * @param request
	 * @param model
	 * @param pageView
	 * @return
	 */
	@RequestMapping(value = "/shop/orderList")
	public ModelAndView orderList(HttpServletRequest request, Model model, PageView pageView) {
		ShopUser shopUser = (ShopUser) request.getSession().getAttribute(Constants.USER_SESSION);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sid", shopUser.getId());

		int totalRecord = orderService.getOrderCount(param);
		if (totalRecord == 0) {
			return new ModelAndView("order/orderList");
		}

		pageView.setPageSize(10);

		param.put("endSize", pageView.getFirstResult());
		param.put("pageSize", pageView.getPageSize());
		param.put("sortColumns", "id");

		List list = orderService.getSplitOrderList(param);

		pageView.setTotalRecord(totalRecord);
		pageView.setRecords(list);

		model.addAttribute("pageView", pageView);

		return new ModelAndView("order/orderList");
	}

	/***
	 * 订单明细
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/shop/orderDetails")
	public ModelAndView orderList(HttpServletRequest request, Model model) {
		Order order = orderService.getOrder(Utils.strToInteger(request.getParameter("id")));
		model.addAttribute("order", order);
		return new ModelAndView("order/orderDetails");
	}

}
