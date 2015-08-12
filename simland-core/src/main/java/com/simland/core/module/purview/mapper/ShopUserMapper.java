package com.simland.core.module.purview.mapper;

import java.util.List;
import java.util.Map;

import com.simland.core.module.purview.entity.ShopUser;

public interface ShopUserMapper {

	public Integer insertShopUser(ShopUser shopUser);

	public Integer updateShopUser(ShopUser shopUser);

	public Integer deleteShopUser(Integer id);

	public ShopUser getShopUser(Map param);

	public List getShopUserList(Map param);

	public Integer getShopUserCount(Map param);

	public List getSplitShopUserList(Map param);

}
