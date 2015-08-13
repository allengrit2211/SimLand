package com.simland.core.module.shop.mapper;

import java.util.List;
import java.util.Map;

import com.simland.core.module.shop.entity.CommodityDetails;

public interface CommodityDetailsMapper{
	
	public Integer insertCommodityDetails(CommodityDetails commodityDetails);  	
	
	public Integer updateCommodityDetails(CommodityDetails commodityDetails);
	
	public Integer deleteCommodityDetails(Integer id);

	public CommodityDetails getCommodityDetails(Map param);
	
	public List getCommodityDetailsList(Map param);

	public Integer getCommodityDetailsCount(Map param);
	
	public List getSplitCommodityDetailsList(Map param);

}
