package com.simland.core.module.shop.mapper;

import java.util.List;
import java.util.Map;

import com.simland.core.module.shop.entity.ShopBanner;

public interface ShopBannerMapper {

	public Integer insertShopBanner(ShopBanner shopBanner);

	public Integer updateShopBanner(ShopBanner shopBanner);

	public Integer deleteShopBanner(Integer id);

	public ShopBanner getShopBanner(Map param);

	public List getShopBannerList(Map param);

	public Integer getShopBannerCount(Map param);

	public List getSplitShopBannerList(Map param);

}
