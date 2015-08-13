package com.simland.core.module.purview.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.simland.core.base.Constants;
import com.simland.core.base.Utils;
import com.simland.core.module.purview.entity.ShopUser;

public class UserPermissionsInterceptor implements HandlerInterceptor {

	private static final Log logger = LogFactory.getLog(UserPermissionsInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

		// User user = (User)
		// request.getSession().getAttribute(Constant.SESSION_USER);
		// if (Utils.isObjectNotEmpty(user))
		// return true; // 返回true，则这个方面调用后会接着调用postHandle(),
		// else {
		// response.sendRedirect(request.getContextPath() + "/user/login");
		// return false;
		// }

		ShopUser shopUser = (ShopUser) request.getSession().getAttribute(Constants.USER_SESSION);
		if (Utils.isObjectEmpty(shopUser)) {// 未登录
			redirect(request, response, "/shop/login");
			return false;
		} else {

			Map<String, String> powers = shopUser.getPowers();
			// 没有权限
			if (Utils.isObjectEmpty(powers) || powers.size() == 0) {
				redirect(request, response, "/shop/login");
				return false;
			}

			for (Entry<String, String> e : powers.entrySet()) {
				if (Utils.isObjectEmpty(Utils.notNullTrim(e.getKey())))
					continue;

				logger.info(request.getRequestURI());

				String srcStr = request.getRequestURI();
				String subStr = request.getRequestURI().replace(Utils.notNullTrim(e.getKey()), "");

				logger.info("subStr=" + subStr + " powerUrl=" + e.getKey());

				if (srcStr.equals(subStr + Utils.notNullTrim(e.getKey())))
					return true;
			}

			redirect(request, response, "/permissions.jsp");

			return false;
		}

	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
		String requestType = request.getHeader("X-Requested-With");
		String ajax = request.getParameter("ajax");
		if (Utils.isObjectEmpty(Utils.notNullTrim(requestType))&&Utils.isObjectEmpty(ajax)) {
			response.sendRedirect(request.getContextPath() + url);
		} else {

			response.setContentType("text/html;charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			Map<String, String> param = new HashMap<String, String>();

			if (url.indexOf("login") > -1) {
				param.put("code", "-100");
				param.put("msg", "未登陆");
			} else {
				param.put("code", "-101");
				param.put("msg", "没有权限");
			}

			String callback = request.getParameter("callback");
			if (Utils.isObjectEmpty(callback)) {
				out.println(Utils.objToJson(param));
			} else {
				out.println(Utils.objToJsonp(param, callback));
			}
		}

	}
}
