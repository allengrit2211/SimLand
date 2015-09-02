package com.simland.core.module.shop.mapper;

import java.util.List;
import java.util.Map;

import com.simland.core.module.shop.entity.Inventory;

public interface InventoryMapper {

	public Integer insertInventory(Inventory inventory);

	public Integer updateInventory(Inventory inventory);

	public Integer deleteInventory(Integer id);

	public Integer deleteInventoryByCid(Integer cid);

	public Inventory getInventory(Map param);

	public List getInventoryList(Map param);

	public Integer getInventoryCount(Map param);

	public List getSplitInventoryList(Map param);

	public void insertBatchInventory(List<Inventory> inventorys);

}
