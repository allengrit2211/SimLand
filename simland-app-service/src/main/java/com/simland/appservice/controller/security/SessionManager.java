package com.simland.appservice.controller.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.simland.core.base.Constants;
import com.simland.core.module.user.entity.User;

/**
 * @Title: SessionManager.java
 * @Package com.simland.appservice.controller.security
 * @Description: 用户Session管理
 * @author Gavin
 * @date 2015年7月10日 上午11:09:09
 * @version V1.0
 */
public class SessionManager {

	/***
	 * 获取用户
	 * 
	 * @return
	 */
	public static User getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (User) authentication.getPrincipal();
	}

	/***
	 * 设置用户信息
	 * 
	 * @param user
	 */
	public static void setUser(User user, HttpServletRequest request) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()));
		HttpSession session = request.getSession(true);
		session.setAttribute(Constants.SPRING_SECURITY_CONTEXT, securityContext);
	}

}
