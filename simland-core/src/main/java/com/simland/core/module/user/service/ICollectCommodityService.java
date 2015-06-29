package com.simland.core.module.user.service;

import java.util.List;
import java.util.Map;

import com.simland.core.module.user.entity.CollectCommodity;

@SuppressWarnings("unchecked")
public interface ICollectCommodityService {
	
	public Integer insertCollectCommodity(CollectCommodity collectCommodity);

	public Integer updateCollectCommodity(CollectCommodity collectCommodity);

	public Integer deleteCollectCommodity(Integer id);
	
	public CollectCommodity getCollectCommodity(Map param);
	
	public List<CollectCommodity> getCollectCommodityList(Map param);

	public Integer getCollectCommodityCount(Map param);
	
	public List<CollectCommodity> getSplitCollectCommodityList(Map param);
	
}
