package com.simland.core.module.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simland.core.base.SysMessage;
import com.simland.core.module.user.entity.User;
import com.simland.core.module.user.mapper.UserMapper;
import com.simland.core.module.user.service.IUserService;

@Service("userService")
@Transactional(rollbackFor = java.lang.Exception.class)
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public List<User> getUserList(Map<String, Object> param) {
		return userMapper.getUserList(null);
	}

	@Override
	public boolean login(String uname, String password, SysMessage msg) {
		if ("zhuoer".equals(uname) && "123123".equals(password)) {
			msg.setCode("1");
			msg.setMsg("登录成功");
			return true;
		}else{
			msg.setCode("2");
			msg.setMsg("登录失败");
			return false;
		}
	}
}
