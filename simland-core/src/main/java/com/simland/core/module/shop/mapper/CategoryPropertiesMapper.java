package com.simland.core.module.shop.mapper;

import java.util.List;
import java.util.Map;

import com.simland.core.module.shop.entity.CategoryProperties;

public interface CategoryPropertiesMapper {

	public Integer insertCategoryProperties(CategoryProperties categoryProperties);

	public Integer updateCategoryProperties(CategoryProperties categoryProperties);

	public Integer deleteCategoryProperties(Integer id);

	public CategoryProperties getCategoryProperties(Map param);

	public List getCategoryPropertiesList(Map param);

	public Integer getCategoryPropertiesCount(Map param);

	public List getSplitCategoryPropertiesList(Map param);

	/***
	 * 根据pid获取商品类别属性 根据id正序 获取attr1 ,attr2
	 * 
	 * @param pid
	 * @return
	 */
	public List getCategoryPropertiesByPid(Integer pid);

}
