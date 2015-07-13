package com.simland.core.module.shop.service;

import java.util.List;
import java.util.Map;

import com.simland.core.module.shop.entity.CategoryPropertiesVal;

@SuppressWarnings("unchecked")
public interface ICategoryPropertiesValService {
	
	public Integer insertCategoryPropertiesVal(CategoryPropertiesVal categoryPropertiesVal);

	public Integer updateCategoryPropertiesVal(CategoryPropertiesVal categoryPropertiesVal);

	public Integer deleteCategoryPropertiesVal(Integer id);
	
	public CategoryPropertiesVal getCategoryPropertiesVal(Map param);
	
	public List<CategoryPropertiesVal> getCategoryPropertiesValList(Map param);

	public Integer getCategoryPropertiesValCount(Map param);
	
	public List<CategoryPropertiesVal> getSplitCategoryPropertiesValList(Map param);
	
}
