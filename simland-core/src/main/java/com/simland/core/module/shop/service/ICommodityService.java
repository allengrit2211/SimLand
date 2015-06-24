package com.simland.core.module.shop.service;

import java.util.List;
import java.util.Map;

import com.simland.core.module.shop.entity.Commodity;

@SuppressWarnings("unchecked")
public interface ICommodityService {
	
	public Integer insertCommodity(Commodity commodity);

	public Integer updateCommodity(Commodity commodity);

	public Integer deleteCommodity(Integer id);
	
	public Commodity getCommodity(Map param);
	
	public List<Commodity> getCommodityList(Map param);

	public Integer getCommodityCount(Map param);
	
	public List<Commodity> getSplitCommodityList(Map param);
	
}
