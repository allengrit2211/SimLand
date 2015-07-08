package com.simland.appservice.controller.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.simland.core.base.SysMessage;
import com.simland.core.base.Utils;

public class AjaxAwareLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

	/**
	 * @param loginFormUrl
	 */
	public AjaxAwareLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
	}

	public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException authException) throws IOException, ServletException {
		if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
			//response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");// 对于ajax请求不重定向
			String reJson = null;
			SysMessage msg = new SysMessage();
			msg.setCode("-100");
			msg.setMsg("未登录");
			reJson = Utils.objToJsonp(msg, request.getParameter("callback"));

			response.setContentType("text/plan");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.println(reJson);
			out.flush();
			out.close();

		} else {
			super.commence(request, response, authException);
		}
	}
}