package com.simland.core.module.user.mapper;

import java.util.List;
import java.util.Map;

import com.simland.core.module.user.entity.CollectShop;

public interface CollectShopMapper{
	
	public Integer insertCollectShop(CollectShop collectShop);  	
	
	public Integer updateCollectShop(CollectShop collectShop);
	
	public Integer deleteCollectShop(Integer id);

	public CollectShop getCollectShop(Map param);
	
	public List getCollectShopList(Map param);

	public Integer getCollectShopCount(Map param);
	
	public List getSplitCollectShopList(Map param);

}
