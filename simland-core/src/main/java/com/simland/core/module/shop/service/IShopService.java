package com.simland.core.module.shop.service;

import java.util.List;
import java.util.Map;

import com.simland.core.module.shop.entity.Shop;

/***
 * 商家服务
 * 
 * @author Administrator
 *
 */
public interface IShopService {

	/****
	 * 获取商家列表
	 * 
	 * @param param
	 * @return
	 */
	public List<Shop> getShopList(Map<String, Object> param);

}
