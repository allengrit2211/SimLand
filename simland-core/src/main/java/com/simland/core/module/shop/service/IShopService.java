package com.simland.core.module.shop.service;

import java.util.List;
import java.util.Map;

import com.simland.core.base.SysMessage;
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
	public List<Shop> getSplitShopList(Map<String, Object> param);

	/***
	 * 商家数量
	 * 
	 * @param param
	 * @return
	 */
	public Integer getShopCount(Map param);

	/***
	 * 获取店铺信息
	 * 
	 * @param id
	 * @return
	 */
	public Shop getShop(Integer id);

	/***
	 * 商家登陆
	 * 
	 * @param loginName
	 * @param pwd
	 * @param msg
	 * @return
	 */
	public Shop shopLogin(String loginName, String pwd, SysMessage msg);

	/***
	 * 更新店铺信息
	 * 
	 * @param shop
	 * @return
	 */
	public Integer updateShop(Shop shop);

}
