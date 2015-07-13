package com.simland.core.module.shop.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.simland.core.module.shop.mapper.CategoryPropertiesMapper;
import com.simland.core.module.shop.entity.CategoryProperties;
import com.simland.core.module.shop.service.ICategoryPropertiesService;

@Service("categoryPropertiesService")
@Transactional(rollbackFor = java.lang.Exception.class)
public class CategoryPropertiesServiceImpl implements ICategoryPropertiesService{

	@Autowired
	private CategoryPropertiesMapper categoryPropertiesMapper;
	
	public Integer insertCategoryProperties(CategoryProperties categoryProperties) {
		return categoryPropertiesMapper.insertCategoryProperties(categoryProperties);
	}

	public Integer updateCategoryProperties(CategoryProperties categoryProperties) {
		return categoryPropertiesMapper.updateCategoryProperties(categoryProperties);
	}
	
	public Integer deleteCategoryProperties(Integer id) {
		return categoryPropertiesMapper.deleteCategoryProperties(id);
	}
	
	public CategoryProperties getCategoryProperties(Map param) {
		return (CategoryProperties)categoryPropertiesMapper.getCategoryProperties(param);
	}
	
	public List<CategoryProperties> getCategoryPropertiesList(Map param) {
		return categoryPropertiesMapper.getCategoryPropertiesList(param);
	}
	
	public Integer getCategoryPropertiesCount(Map param) {
		return (Integer)categoryPropertiesMapper.getCategoryPropertiesCount(param);
	}
	
	public List<CategoryProperties> getSplitCategoryPropertiesList(Map param) {
		return categoryPropertiesMapper.getSplitCategoryPropertiesList(param);
	}
	
}
