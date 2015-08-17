package com.simland.core.module.shop.mapper;

import java.util.List;
import java.util.Map;

import com.simland.core.module.shop.entity.Commodity;

public interface CommodityMapper {

	public Integer insertCommodity(Commodity commodity);

	public Integer updateCommodity(Commodity commodity);

	public Integer deleteCommodity(Integer id);

	public Commodity getCommodity(Map param);

	public List getCommodityList(Map param);

	public Integer getCommodityCount(Map param);

	public List getSplitCommodityList(Map param);

	public List getSplitCommodityByInventory(Map param);

	public Integer getSplitCommodityByInventoryCount(Map param);

}
