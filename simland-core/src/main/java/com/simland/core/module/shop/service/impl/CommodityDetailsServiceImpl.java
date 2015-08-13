package com.simland.core.module.shop.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.simland.core.module.shop.mapper.CommodityDetailsMapper;
import com.simland.core.module.shop.entity.CommodityDetails;
import com.simland.core.module.shop.service.ICommodityDetailsService;

@Service("commodityDetailsService")
public class CommodityDetailsServiceImpl implements ICommodityDetailsService {

	@Autowired
	private CommodityDetailsMapper commodityDetailsMapper;

	@Transactional(propagation = Propagation.REQUIRED)
	public Integer insertCommodityDetails(CommodityDetails commodityDetails) {
		return commodityDetailsMapper.insertCommodityDetails(commodityDetails);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Integer updateCommodityDetails(CommodityDetails commodityDetails) {
		return commodityDetailsMapper.updateCommodityDetails(commodityDetails);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Integer deleteCommodityDetails(Integer id) {
		return commodityDetailsMapper.deleteCommodityDetails(id);
	}

	public CommodityDetails getCommodityDetails(Map param) {
		return (CommodityDetails) commodityDetailsMapper.getCommodityDetails(param);
	}

	public List<CommodityDetails> getCommodityDetailsList(Map param) {
		return commodityDetailsMapper.getCommodityDetailsList(param);
	}

	public Integer getCommodityDetailsCount(Map param) {
		return (Integer) commodityDetailsMapper.getCommodityDetailsCount(param);
	}

	public List<CommodityDetails> getSplitCommodityDetailsList(Map param) {
		return commodityDetailsMapper.getSplitCommodityDetailsList(param);
	}

}
