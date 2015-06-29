package com.simland.core.module.user.service;

import java.util.List;
import java.util.Map;

import com.simland.core.module.user.entity.CollectShop;

@SuppressWarnings("unchecked")
public interface ICollectShopService {

	public Integer insertCollectShop(CollectShop collectShop);

	public Integer updateCollectShop(CollectShop collectShop);

	public Integer deleteCollectShop(Integer id);

	public CollectShop getCollectShop(Map param);

	public List<CollectShop> getCollectShopList(Map param);

	public Integer getCollectShopCount(Map param);

	public List<CollectShop> getSplitCollectShopList(Map param);

}
