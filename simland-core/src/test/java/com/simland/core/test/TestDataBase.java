package com.simland.core.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.simland.core.module.user.mapper.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class TestDataBase {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void select() {
		System.out.println(userMapper);
		System.out.println(userMapper.getUserList(null));
	}

}
