package com.simland.core.module.user.mapper;

import java.util.List;
import java.util.Map;

import com.simland.core.module.user.entity.CollectCommodity;

public interface CollectCommodityMapper{
	
	public Integer insertCollectCommodity(CollectCommodity collectCommodity);  	
	
	public Integer updateCollectCommodity(CollectCommodity collectCommodity);
	
	public Integer deleteCollectCommodity(Integer id);

	public CollectCommodity getCollectCommodity(Map param);
	
	public List getCollectCommodityList(Map param);

	public Integer getCollectCommodityCount(Map param);
	
	public List getSplitCollectCommodityList(Map param);

}
