package com.simland.core.module.shop.mapper;

import java.util.List;
import java.util.Map;

import com.simland.core.module.shop.entity.Shop;

public interface ShopMapper{
	
	public Integer insertShop(Shop shop);  	
	
	public Integer updateShop(Shop shop);
	
	public Integer deleteShop(Integer id);

	public Shop getShop(Map param);
	
	public List getShopList(Map param);

	public Integer getShopCount(Map param);
	
	public List getSplitShopList(Map param);

}
