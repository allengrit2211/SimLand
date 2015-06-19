package com.simland.core.module.user.mapper;

import java.util.List;
import java.util.Map;

import com.simland.core.module.user.entity.User;

public interface UserMapper{
	
	public Integer insertUser(User user);  	
	
	public Integer updateUser(User user);
	
	public Integer deleteUser(Integer id);

	public User getUser(Map param);
	
	public List getUserList(Map param);

	public Integer getUserCount(Map param);
	
	public List getSplitUserList(Map param);

}
