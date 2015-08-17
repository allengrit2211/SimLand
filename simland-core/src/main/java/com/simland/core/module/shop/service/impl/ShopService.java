package com.simland.core.module.shop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.simland.core.base.MD5Util;
import com.simland.core.base.SysMessage;
import com.simland.core.base.Utils;
import com.simland.core.module.shop.entity.Shop;
import com.simland.core.module.shop.mapper.ShopMapper;
import com.simland.core.module.shop.service.IShopService;

@Service("shopService")
@Transactional(readOnly = true)
public class ShopService implements IShopService {

	@Autowired
	private ShopMapper shopMapper;

	@Override
	public List<Shop> getSplitShopList(Map<String, Object> param) {
		return shopMapper.getSplitShopList(param);
	}

	@Override
	public Integer getShopCount(Map param) {
		return shopMapper.getShopCount(param);
	}

	@Override
	public Shop getShop(Integer id) {
		if (Utils.isObjectEmpty(id))
			return null;

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		Shop shop = shopMapper.getShop(param);
		param = null;
		return shop;
	}

	@Override
	public Shop shopLogin(String loginName, String pwd, SysMessage msg) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loginName", loginName);
		List<Shop> shops = shopMapper.getShopList(param);
		if (shops == null || shops.size() == 0)
			return null;

		if (shops.get(0).getLoginPwd().equalsIgnoreCase(MD5Util.md5Hex(Utils.notNullTrim(pwd)))) {
			return shops.get(0);
		}

		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Integer updateShop(Shop shop) {
		return shopMapper.updateShop(shop);
	}

}
