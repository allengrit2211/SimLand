package com.simland.core.module.shop.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.simland.core.base.SystemConstants;

public class Commodity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.Integer id;
	private java.lang.Integer sid;
	private java.lang.String name;
	private java.lang.String img;
	private java.lang.Integer type;
	private java.lang.Integer collectNum;
	private java.lang.Double marketPrice;
	private java.lang.Double realPrice;
	private java.util.Date createTime;
	private java.lang.String creater;
	private java.lang.Integer isNew;
	private java.lang.Integer isSpecial;

	private CategoryProperties attr1;// 属性1
	private CategoryProperties attr2;// 属性2

	private List<String[]> attr1List = new ArrayList<String[]>();// 属性1值
	private List<String[]> attr2List = new ArrayList<String[]>();// 属性2值

	private String[] defaultChose = new String[] { "", "", "", "" };// 默认选择颜色尺码
																	// id,id,val,val

	private List<CommodityInventory> cInventoryList;

	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}

	public java.lang.Integer getSid() {
		return this.sid;
	}

	public void setSid(java.lang.Integer value) {
		this.sid = value;
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String value) {
		this.name = value;
	}

	public java.lang.String getImg() {
		return this.img;
	}

	public void setImg(java.lang.String value) {
		this.img = value;
	}

	public java.lang.Integer getType() {
		return this.type;
	}

	public void setType(java.lang.Integer value) {
		this.type = value;
	}

	public java.lang.Integer getCollectNum() {
		return this.collectNum;
	}

	public void setCollectNum(java.lang.Integer value) {
		this.collectNum = value;
	}

	public java.lang.Double getMarketPrice() {
		return this.marketPrice;
	}

	public void setMarketPrice(java.lang.Double value) {
		this.marketPrice = value;
	}

	public java.lang.Double getRealPrice() {
		return this.realPrice;
	}

	public void setRealPrice(java.lang.Double value) {
		this.realPrice = value;
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}

	public java.lang.String getCreater() {
		return this.creater;
	}

	public void setCreater(java.lang.String value) {
		this.creater = value;
	}

	public java.lang.Integer getIsNew() {
		return this.isNew;
	}

	public void setIsNew(java.lang.Integer value) {
		this.isNew = value;
	}

	public java.lang.Integer getIsSpecial() {
		return this.isSpecial;
	}

	public void setIsSpecial(java.lang.Integer value) {
		this.isSpecial = value;
	}

	public CategoryProperties getAttr1() {

		if (attr1 != null)
			return attr1;

		CategoryProperties cp = SystemConstants.categoryPropertiesMap.get(this.type + "");
		if (cp != null && cp.getCategoryProperties1() != null)
			this.attr1 = cp.getCategoryProperties1();

		return attr1;
	}

	public void setAttr1(CategoryProperties attr1) {
		this.attr1 = attr1;
	}

	public CategoryProperties getAttr2() {

		if (attr2 != null)
			return attr2;

		CategoryProperties cp = SystemConstants.categoryPropertiesMap.get(this.type + "");
		if (cp != null && cp.getCategoryProperties2() != null)
			return this.attr2 = cp.getCategoryProperties2();

		return attr2;
	}

	public void setAttr2(CategoryProperties attr2) {
		this.attr2 = attr2;
	}

	public List<String[]> getAttr1List() {
		return attr1List;
	}

	public void setAttr1List(List<String[]> attr1List) {
		this.attr1List = attr1List;
	}

	public List<String[]> getAttr2List() {
		return attr2List;
	}

	public void setAttr2List(List<String[]> attr2List) {
		this.attr2List = attr2List;
	}

	public List<CommodityInventory> getcInventoryList() {
		return cInventoryList;
	}

	public String[] getDefaultChose() {
		return defaultChose;
	}

	public void setDefaultChose(String[] defaultChose) {
		this.defaultChose = defaultChose;
	}

	/***
	 * 商品库存查询
	 * 
	 * @param cInventoryList
	 */
	public void setcInventoryList(List<CommodityInventory> cInventoryList) {

		boolean flag = true;

		Map<String, String> attrMap1 = new TreeMap<String, String>();
		Map<String, String> attrMap2 = new TreeMap<String, String>();
		for (int i = 0; cInventoryList != null && i < cInventoryList.size(); i++) {
			CommodityInventory ci = cInventoryList.get(i);
			attrMap1.put(String.valueOf(ci.getAttr1()), ci.getCpvalue1());
			attrMap2.put(String.valueOf(ci.getAttr2()), ci.getCpvalue2());

			if (flag && ci.getNums() > 0) {
				defaultChose = new String[] { String.valueOf(ci.getAttr1()), String.valueOf(ci.getAttr2()), String.valueOf(ci.getCpvalue1()), String.valueOf(ci.getCpvalue2()) };
				flag = false;
			}
		}

		for (Entry<String, String> e : attrMap1.entrySet()) {
			attr1List.add(new String[] { e.getKey(), e.getValue() });
		}

		for (Entry<String, String> e : attrMap2.entrySet()) {
			attr2List.add(new String[] { e.getKey(), e.getValue() });
		}

		attrMap1 = null;
		attrMap2 = null;

		this.cInventoryList = cInventoryList;
	}

	/***
	 * 获取商品sku
	 * 
	 * @param cid
	 * @param attr1
	 * @param attr2
	 * @return
	 */
	public static String getCommoditySku(int cid, int attr1, int attr2) {
		return String.valueOf(cid) + String.valueOf(Math.max(attr1, attr2)) + String.valueOf(Math.min(attr1, attr2));
	}

	/***
	 * 获取商品sku
	 * 
	 * @param c
	 * @return
	 */
	public static String getCommoditySku(Commodity c) {
		return getCommoditySku(c.getId(), c.getAttr1() == null ? 0 : c.getAttr1().getId(), c.getAttr2() == null ? 0 : c.getAttr2().getId());
	}
}
