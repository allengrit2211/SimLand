package com.simland.core.base;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceLocator {

	public static BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/spring-config.xml");

	public static Object getBean(String name) {
		return beanFactory.getBean(name);
	}

}