package com.simland.core.module.shop.service;

import java.util.List;
import java.util.Map;

import com.simland.core.module.shop.entity.CommodityDetails;

@SuppressWarnings("unchecked")
public interface ICommodityDetailsService {
	
	public Integer insertCommodityDetails(CommodityDetails commodityDetails);

	public Integer updateCommodityDetails(CommodityDetails commodityDetails);

	public Integer deleteCommodityDetails(Integer id);
	
	public CommodityDetails getCommodityDetails(Map param);
	
	public List<CommodityDetails> getCommodityDetailsList(Map param);

	public Integer getCommodityDetailsCount(Map param);
	
	public List<CommodityDetails> getSplitCommodityDetailsList(Map param);
	
}
