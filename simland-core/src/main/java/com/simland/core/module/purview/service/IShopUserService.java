package com.simland.core.module.purview.service;

import java.util.List;
import java.util.Map;

import com.simland.core.module.purview.entity.ShopUser;

@SuppressWarnings("unchecked")
public interface IShopUserService {
	
	public Integer insertShopUser(ShopUser shopUser);

	public Integer updateShopUser(ShopUser shopUser);

	public Integer deleteShopUser(Integer id);
	
	public ShopUser getShopUser(Map param);
	
	public List<ShopUser> getShopUserList(Map param);

	public Integer getShopUserCount(Map param);
	
	public List<ShopUser> getSplitShopUserList(Map param);
	
}
