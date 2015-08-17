package com.simland.core.module.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.simland.core.base.Utils;
import com.simland.core.module.shop.entity.Shop;
import com.simland.core.module.shop.mapper.CommodityMapper;
import com.simland.core.module.shop.mapper.ShopMapper;
import com.simland.core.module.user.entity.CollectShop;
import com.simland.core.module.user.mapper.CollectShopMapper;
import com.simland.core.module.user.service.ICollectShopService;

@Service("collectShopService")
@Transactional(readOnly=true)
public class CollectShopServiceImpl implements ICollectShopService {

	@Autowired
	private CollectShopMapper collectShopMapper;

	@Autowired
	private ShopMapper shopMapper;

	@Autowired
	private CommodityMapper commodityMapper;

	@Transactional(propagation = Propagation.REQUIRED)
	public Integer insertCollectShop(CollectShop collectShop) {

		int id = collectShopMapper.insertCollectShop(collectShop);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sid", collectShop.getSid());
		int count = collectShopMapper.getCollectShopCount(param);
		if (count > 0) {
			Shop shop = new Shop();
			shop.setId(collectShop.getSid());
			shop.setCollectNum(count);
			shopMapper.updateShop(shop);
			shop = null;
		}
		param = null;
		return id;

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Integer updateCollectShop(CollectShop collectShop) {
		return collectShopMapper.updateCollectShop(collectShop);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Integer deleteCollectShop(Integer id) {
		return collectShopMapper.deleteCollectShop(id);
	}

	public CollectShop getCollectShop(Map param) {
		return (CollectShop) collectShopMapper.getCollectShop(param);
	}

	public List<CollectShop> getCollectShopList(Map param) {
		return collectShopMapper.getCollectShopList(param);
	}

	public Integer getCollectShopCount(Map param) {
		return (Integer) collectShopMapper.getCollectShopCount(param);
	}

	public List<CollectShop> getSplitCollectShopList(Map param) {
		List<CollectShop> list = collectShopMapper.getSplitCollectShopList(param);

		Map<String, Object> param1 = new HashMap<String, Object>();
		for (int i = 0; list != null && i < list.size(); i++) {
			String recomm = list.get(i).getRecomm();
			if (Utils.isObjectNotEmpty(recomm)) {
				param1.clear();
				param1.put("ids", Utils.getStrs(recomm.split(";")));
				List cl = commodityMapper.getCommodityList(param1);
				list.get(i).setRclist(cl);
			}
		}
		param1 = null;
		return list;
	}

}
