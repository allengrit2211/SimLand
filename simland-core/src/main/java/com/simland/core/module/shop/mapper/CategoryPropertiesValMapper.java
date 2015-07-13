package com.simland.core.module.shop.mapper;

import java.util.List;
import java.util.Map;

import com.simland.core.module.shop.entity.CategoryPropertiesVal;

public interface CategoryPropertiesValMapper{
	
	public Integer insertCategoryPropertiesVal(CategoryPropertiesVal categoryPropertiesVal);  	
	
	public Integer updateCategoryPropertiesVal(CategoryPropertiesVal categoryPropertiesVal);
	
	public Integer deleteCategoryPropertiesVal(Integer id);

	public CategoryPropertiesVal getCategoryPropertiesVal(Map param);
	
	public List getCategoryPropertiesValList(Map param);

	public Integer getCategoryPropertiesValCount(Map param);
	
	public List getSplitCategoryPropertiesValList(Map param);

}
