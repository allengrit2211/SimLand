package com.simland.core.module.shop.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simland.core.base.Utils;
import com.simland.core.module.shop.entity.ShopBanner;
import com.simland.core.module.shop.mapper.ShopBannerMapper;
import com.simland.core.module.shop.service.IShopBannerService;

@Service("shopBannerService")
@Transactional(readOnly = true)
public class ShopBannerServiceImpl implements IShopBannerService {

	@Autowired
	private ShopBannerMapper shopBannerMapper;

	@Transactional(readOnly = false)
	public Integer insertShopBanner(ShopBanner shopBanner) {
		return shopBannerMapper.insertShopBanner(shopBanner);
	}

	@Transactional(readOnly = false)
	public Integer insertShopBanner(List<ShopBanner> shopBanners) {

		for (ShopBanner shopBanner : shopBanners) {
			if (Utils.isObjectEmpty(shopBanner.getId()) || shopBanner.getId().intValue() <= 0) {
				shopBannerMapper.insertShopBanner(shopBanner);
			} else {
				shopBannerMapper.updateShopBanner(shopBanner);
			}
		}
		return 1;
	}

	@Transactional(readOnly = false)
	public Integer updateShopBanner(ShopBanner shopBanner) {
		return shopBannerMapper.updateShopBanner(shopBanner);
	}

	@Transactional(readOnly = false)
	public Integer deleteShopBanner(Integer id) {
		return shopBannerMapper.deleteShopBanner(id);
	}

	public ShopBanner getShopBanner(Map param) {
		return (ShopBanner) shopBannerMapper.getShopBanner(param);
	}

	public List<ShopBanner> getShopBannerList(Map param) {
		return shopBannerMapper.getShopBannerList(param);
	}

	public Integer getShopBannerCount(Map param) {
		return (Integer) shopBannerMapper.getShopBannerCount(param);
	}

	public List<ShopBanner> getSplitShopBannerList(Map param) {
		return shopBannerMapper.getSplitShopBannerList(param);
	}

}
