package com.simland.core.module.shop.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simland.core.module.shop.entity.CategoryPropertiesVal;
import com.simland.core.module.shop.entity.Commodity;
import com.simland.core.module.shop.entity.Inventory;
import com.simland.core.module.shop.mapper.CategoryPropertiesValMapper;
import com.simland.core.module.shop.mapper.CommodityMapper;
import com.simland.core.module.shop.mapper.InventoryMapper;
import com.simland.core.module.shop.service.IInventoryService;

@Service("inventoryService")
@Transactional(rollbackFor = java.lang.Exception.class)
public class InventoryServiceImpl implements IInventoryService {

	@Autowired
	private InventoryMapper inventoryMapper;

	@Autowired
	private CommodityMapper commodityMapper;

	@Autowired
	private CategoryPropertiesValMapper categoryPropertiesValMapper;

	public Integer insertInventory(Inventory inventory) {
		return inventoryMapper.insertInventory(inventory);
	}

	public Integer updateInventory(Inventory inventory) {
		return inventoryMapper.updateInventory(inventory);
	}

	public Integer deleteInventory(Integer id) {
		return inventoryMapper.deleteInventory(id);
	}

	public Inventory getInventory(Map param) {
		return (Inventory) inventoryMapper.getInventory(param);
	}

	public List<Inventory> getInventoryList(Map param) {
		return inventoryMapper.getInventoryList(param);
	}

	public Integer getInventoryCount(Map param) {
		return (Integer) inventoryMapper.getInventoryCount(param);
	}

	public List<Inventory> getSplitInventoryList(Map param) {
		return inventoryMapper.getSplitInventoryList(param);
	}

	@Override
	public Integer insertInventory(Commodity commodity, List<Inventory> inventorys,
			List<CategoryPropertiesVal> categoryPropertiesVals) {
		
		int id = commodityMapper.insertCommodity(commodity);
		
		for (Inventory inventory : inventorys) {
			inventory.setCid(commodity.getId());
		}
		
		for (CategoryPropertiesVal categoryPropertiesVal : categoryPropertiesVals) {
			categoryPropertiesVal.setCid(commodity.getId());
		}
		
		categoryPropertiesValMapper.insertBatchCategoryPropertiesVal(categoryPropertiesVals);
		inventoryMapper.insertBatchInventory(inventorys);
		
		
		return id;
	}

}
