package com.simland.appservice.base.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.simland.core.base.SystemConstants;
import com.simland.core.module.shop.entity.CategoryProperties;
import com.simland.core.module.shop.service.ICategoryPropertiesService;

public class ServletListener implements ServletContextListener {

	@Autowired
	@Qualifier("categoryPropertiesService")
	private ICategoryPropertiesService categoryPropertiesService;

	private static Logger logger = Logger.getLogger(ServletListener.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		try {


			
			ServletContext servletContext = servletContextEvent.getServletContext();
	        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
	       
	        
	        ICategoryPropertiesService categoryPropertiesService = (ICategoryPropertiesService)context.getBean("categoryPropertiesService");
	        
			// 加载基础属性到内存当中
			List<CategoryProperties> cplist = categoryPropertiesService.getCategoryPropertiesList(null);
			SystemConstants.setCategoryPropertiesMap(cplist);

		} catch (Exception e) {
			logger.error("ServletListener error:" + e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
