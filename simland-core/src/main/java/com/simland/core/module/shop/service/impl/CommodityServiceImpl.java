package com.simland.core.module.shop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.simland.core.module.shop.entity.Commodity;
import com.simland.core.module.shop.mapper.CategoryPropertiesMapper;
import com.simland.core.module.shop.mapper.CommodityMapper;
import com.simland.core.module.shop.service.ICommodityService;

@Service("commodityService")
@Transactional(readOnly = true)
public class CommodityServiceImpl implements ICommodityService {

	@Autowired
	private CommodityMapper commodityMapper;

	@Autowired
	private CategoryPropertiesMapper categoryPropertiesMapper;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Integer insertCommodity(Commodity commodity) {
		return commodityMapper.insertCommodity(commodity);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Integer updateCommodity(Commodity commodity) {
		return commodityMapper.updateCommodity(commodity);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Integer deleteCommodity(Integer id) {
		return commodityMapper.deleteCommodity(id);
	}

	public Commodity getCommodity(Map param) {
		return (Commodity) commodityMapper.getCommodity(param);
	}

	public List<Commodity> getCommodityList(Map param) {
		return commodityMapper.getCommodityList(param);
	}

	public Integer getCommodityCount(Map param) {
		return (Integer) commodityMapper.getCommodityCount(param);
	}

	public List<Commodity> getSplitCommodityList(Map param) {
		return commodityMapper.getSplitCommodityList(param);
	}

	@Override
	public Commodity getCommodity(Integer id) {

		if (id == null || id <= 0)
			return null;

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		Commodity c = commodityMapper.getCommodity(param);
		param = null;
		return c;
	}

	/***
	 * 根据库存获取商品
	 * 
	 * @param param
	 * @return
	 */
	public List getSplitCommodityByInventory(Map param) {
		return commodityMapper.getSplitCommodityByInventory(param);
	}

	public Integer getSplitCommodityByInventoryCount(Map param) {
		return commodityMapper.getSplitCommodityByInventoryCount(param);
	}

}
