package com.simland.core.module.shop.service;

import java.util.List;
import java.util.Map;

import com.simland.core.module.shop.entity.CategoryProperties;

@SuppressWarnings("unchecked")
public interface ICategoryPropertiesService {
	
	public Integer insertCategoryProperties(CategoryProperties categoryProperties);

	public Integer updateCategoryProperties(CategoryProperties categoryProperties);

	public Integer deleteCategoryProperties(Integer id);
	
	public CategoryProperties getCategoryProperties(Map param);
	
	public List<CategoryProperties> getCategoryPropertiesList(Map param);

	public Integer getCategoryPropertiesCount(Map param);
	
	public List<CategoryProperties> getSplitCategoryPropertiesList(Map param);
	
}
