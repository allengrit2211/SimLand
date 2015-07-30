package com.simland.core.module.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.simland.core.base.SysMessage;
import com.simland.core.module.user.entity.User;

public interface IUserService {

	/***
	 * 用户登录
	 * 
	 * @param uname
	 * @param password
	 * @param msg
	 * @return
	 */
	public User login(String uname, String password, SysMessage msg);

	public List<User> getUserList(Map<String, Object> param);
	
	public User getUser(Integer id);
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
