package com.simland.core.module.shop.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.simland.core.module.shop.mapper.CommodityMapper;
import com.simland.core.module.shop.entity.Commodity;
import com.simland.core.module.shop.service.ICommodityService;

@Service("commodityService")
@Transactional(rollbackFor = java.lang.Exception.class)
public class CommodityServiceImpl implements ICommodityService {

	@Autowired
	private CommodityMapper commodityMapper;

	public Integer insertCommodity(Commodity commodity) {
		return commodityMapper.insertCommodity(commodity);
	}

	public Integer updateCommodity(Commodity commodity) {
		return commodityMapper.updateCommodity(commodity);
	}

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

}
