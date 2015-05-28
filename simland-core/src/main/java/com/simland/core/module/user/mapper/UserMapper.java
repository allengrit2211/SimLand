package com.simland.core.module.user.mapper;

import java.util.List;
import java.util.Map;

import com.simland.core.module.user.entity.User;

public interface UserMapper {


	public List<User> getUserList(Map<String, Object> param);

}
