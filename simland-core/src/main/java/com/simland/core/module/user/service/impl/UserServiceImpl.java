package com.simland.core.module.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simland.core.base.MD5Util;
import com.simland.core.base.SysMessage;
import com.simland.core.base.Utils;
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
	public User login(String uname, String password, SysMessage msg) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("uname", uname);
		User user = userMapper.getUser(param);
		if (Utils.isObjectEmpty(user)) {
			msg.setCode("1");
			msg.setMsg("用户名不存在");
			return null;
		}

		if (user.getPassword().equalsIgnoreCase(
				MD5Util.encode(password.getBytes()))) {
			msg.setCode("2");
			msg.setMsg("登录成功");
			return user;
		} else {
			msg.setCode("3");
			msg.setMsg("用户名或密码错误");
			return null;
		}

	}
}
