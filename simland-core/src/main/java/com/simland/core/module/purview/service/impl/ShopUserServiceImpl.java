package com.simland.core.module.purview.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simland.core.module.purview.entity.ShopUser;
import com.simland.core.module.purview.mapper.ShopUserMapper;
import com.simland.core.module.purview.service.IShopUserService;

@Service("shopUserService")
@Transactional(rollbackFor = java.lang.Exception.class)
public class ShopUserServiceImpl implements IShopUserService{

	@Autowired
	private ShopUserMapper shopUserMapper;
	
	public Integer insertShopUser(ShopUser shopUser) {
		return shopUserMapper.insertShopUser(shopUser);
	}

	public Integer updateShopUser(ShopUser shopUser) {
		return shopUserMapper.updateShopUser(shopUser);
	}
	
	public Integer deleteShopUser(Integer id) {
		return shopUserMapper.deleteShopUser(id);
	}
	
	public ShopUser getShopUser(Map param) {
		return (ShopUser)shopUserMapper.getShopUser(param);
	}
	
	public List<ShopUser> getShopUserList(Map param) {
		return shopUserMapper.getShopUserList(param);
	}
	
	public Integer getShopUserCount(Map param) {
		return (Integer)shopUserMapper.getShopUserCount(param);
	}
	
	public List<ShopUser> getSplitShopUserList(Map param) {
		return shopUserMapper.getSplitShopUserList(param);
	}
	
}
