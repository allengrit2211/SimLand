package com.simland.core.module.shop.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.simland.core.module.shop.mapper.CategoryPropertiesValMapper;
import com.simland.core.module.shop.entity.CategoryPropertiesVal;
import com.simland.core.module.shop.service.ICategoryPropertiesValService;

@Service("categoryPropertiesValService")
@Transactional(rollbackFor = java.lang.Exception.class)
public class CategoryPropertiesValServiceImpl implements ICategoryPropertiesValService{

	@Autowired
	private CategoryPropertiesValMapper categoryPropertiesValMapper;
	
	public Integer insertCategoryPropertiesVal(CategoryPropertiesVal categoryPropertiesVal) {
		return categoryPropertiesValMapper.insertCategoryPropertiesVal(categoryPropertiesVal);
	}

	public Integer updateCategoryPropertiesVal(CategoryPropertiesVal categoryPropertiesVal) {
		return categoryPropertiesValMapper.updateCategoryPropertiesVal(categoryPropertiesVal);
	}
	
	public Integer deleteCategoryPropertiesVal(Integer id) {
		return categoryPropertiesValMapper.deleteCategoryPropertiesVal(id);
	}
	
	public CategoryPropertiesVal getCategoryPropertiesVal(Map param) {
		return (CategoryPropertiesVal)categoryPropertiesValMapper.getCategoryPropertiesVal(param);
	}
	
	public List<CategoryPropertiesVal> getCategoryPropertiesValList(Map param) {
		return categoryPropertiesValMapper.getCategoryPropertiesValList(param);
	}
	
	public Integer getCategoryPropertiesValCount(Map param) {
		return (Integer)categoryPropertiesValMapper.getCategoryPropertiesValCount(param);
	}
	
	public List<CategoryPropertiesVal> getSplitCategoryPropertiesValList(Map param) {
		return categoryPropertiesValMapper.getSplitCategoryPropertiesValList(param);
	}
	
}
