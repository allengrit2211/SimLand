package com.simland.core.module.shop.service;

import java.util.List;
import java.util.Map;

import com.simland.core.module.shop.entity.ShopBanner;

@SuppressWarnings("unchecked")
public interface IShopBannerService {

	public Integer insertShopBanner(List<ShopBanner> sBanners);

	public Integer updateShopBanner(ShopBanner shopBanner);

	public Integer deleteShopBanner(Integer id);

	public ShopBanner getShopBanner(Map param);

	public List<ShopBanner> getShopBannerList(Map param);

	public Integer getShopBannerCount(Map param);

	public List<ShopBanner> getSplitShopBannerList(Map param);

}
