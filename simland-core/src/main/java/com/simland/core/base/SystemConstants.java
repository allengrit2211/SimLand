package com.simland.core.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.simland.core.module.shop.entity.CategoryProperties;

/**
 * @Title: SystemConstants.java
 * @Package com.simland.appservice.base
 * @Description: 系统常量类
 * @author Gavin
 * @date 2015年7月9日 下午4:25:16
 * @version V1.0
 */
public class SystemConstants {

	/***
	 * 商品类别属性值
	 */
	public static Map<String, CategoryProperties> categoryPropertiesMap = new HashMap<String, CategoryProperties>();// 商品属性类别

	public static void setCategoryPropertiesMap(List<CategoryProperties> cplist) {
		if (cplist != null && cplist.size() != 0) {
			for (CategoryProperties c : cplist) {
				categoryPropertiesMap.put(String.valueOf(c.getId()), c);
			}
		}
	}

}
