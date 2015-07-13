package com.simland.core.module.shop.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.simland.core.module.shop.mapper.InventoryMapper;
import com.simland.core.module.shop.entity.Inventory;
import com.simland.core.module.shop.service.IInventoryService;

@Service("inventoryService")
@Transactional(rollbackFor = java.lang.Exception.class)
public class InventoryServiceImpl implements IInventoryService{

	@Autowired
	private InventoryMapper inventoryMapper;
	
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
		return (Inventory)inventoryMapper.getInventory(param);
	}
	
	public List<Inventory> getInventoryList(Map param) {
		return inventoryMapper.getInventoryList(param);
	}
	
	public Integer getInventoryCount(Map param) {
		return (Integer)inventoryMapper.getInventoryCount(param);
	}
	
	public List<Inventory> getSplitInventoryList(Map param) {
		return inventoryMapper.getSplitInventoryList(param);
	}
	
}
