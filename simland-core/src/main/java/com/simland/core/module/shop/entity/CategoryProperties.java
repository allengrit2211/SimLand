package com.simland.core.module.shop.entity;

import java.util.List;

public class CategoryProperties implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;
	private java.lang.Integer pid;
	private java.lang.String name;
	private java.lang.Integer ilevel;
	private java.lang.Integer attrMark;

	private List<CategoryProperties> categoryPropertiesList;// 子节点
	private List<CategoryPropertiesVal> categoryPropertiesValList;

	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}

	public java.lang.Integer getPid() {
		return this.pid;
	}

	public void setPid(java.lang.Integer value) {
		this.pid = value;
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String value) {
		this.name = value;
	}

	public java.lang.Integer getIlevel() {
		return this.ilevel;
	}

	public void setIlevel(java.lang.Integer value) {
		this.ilevel = value;
	}

	public java.lang.Integer getAttrMark() {
		return this.attrMark;
	}

	public void setAttrMark(java.lang.Integer value) {
		this.attrMark = value;
	}

	public List<CategoryPropertiesVal> getCategoryPropertiesValList() {
		return categoryPropertiesValList;
	}

	public void setCategoryPropertiesValList(List<CategoryPropertiesVal> categoryPropertiesValList) {
		this.categoryPropertiesValList = categoryPropertiesValList;
	}

	public List<CategoryProperties> getCategoryPropertiesList() {
		return categoryPropertiesList;
	}

	public void setCategoryPropertiesList(List<CategoryProperties> categoryPropertiesList) {
		this.categoryPropertiesList = categoryPropertiesList;
	}

	public CategoryProperties getCategoryProperties1() {
		if (this.categoryPropertiesList != null && this.categoryPropertiesList.size() > 0)
			return this.categoryPropertiesList.get(0);
		else
			return null;
	}

	public CategoryProperties getCategoryProperties2() {
		if (this.categoryPropertiesList != null && this.categoryPropertiesList.size() > 1)
			return this.categoryPropertiesList.get(1);
		else
			return null;
	}

}
