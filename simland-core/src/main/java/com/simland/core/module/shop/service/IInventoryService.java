package com.simland.core.module.shop.service;

import java.util.List;
import java.util.Map;

import com.simland.core.base.SysMessage;
import com.simland.core.module.shop.entity.CategoryPropertiesVal;
import com.simland.core.module.shop.entity.Commodity;
import com.simland.core.module.shop.entity.Inventory;

@SuppressWarnings("unchecked")
public interface IInventoryService {

	public Integer insertInventory(Inventory inventory);

	public Integer insertInventory(Commodity commodity,List<Inventory> inventorys, List<CategoryPropertiesVal> categoryPropertiesVals,SysMessage sysMessage);

	public Integer updateInventory(Inventory inventory);

	public Integer deleteInventory(Integer id);

	public Inventory getInventory(Map param);

	public List<Inventory> getInventoryList(Map param);

	public Integer getInventoryCount(Map param);

	public List<Inventory> getSplitInventoryList(Map param);

}
