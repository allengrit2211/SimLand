package com.simland.core.module.shop.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simland.core.base.Cartesian;
import com.simland.core.base.SysMessage;
import com.simland.core.base.Utils;
import com.simland.core.module.shop.entity.CategoryPropertiesVal;
import com.simland.core.module.shop.entity.Commodity;
import com.simland.core.module.shop.entity.Inventory;
import com.simland.core.module.shop.mapper.CategoryPropertiesValMapper;
import com.simland.core.module.shop.mapper.CommodityDetailsMapper;
import com.simland.core.module.shop.mapper.CommodityMapper;
import com.simland.core.module.shop.mapper.InventoryMapper;
import com.simland.core.module.shop.service.IInventoryService;

@Service("inventoryService")
@Transactional(readOnly = true)
public class InventoryServiceImpl implements IInventoryService {

	@Autowired
	private InventoryMapper inventoryMapper;

	@Autowired
	private CommodityMapper commodityMapper;

	@Autowired
	private CommodityDetailsMapper commodityDetailsMapper;

	@Autowired
	private CategoryPropertiesValMapper categoryPropertiesValMapper;

	@Transactional(readOnly = false)
	public Integer insertInventory(Inventory inventory) {
		return inventoryMapper.insertInventory(inventory);
	}

	@Transactional(readOnly = false)
	public Integer updateInventory(Inventory inventory) {
		return inventoryMapper.updateInventory(inventory);
	}

	@Transactional(readOnly = false)
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
	@Transactional(readOnly = false)
	public Integer insertInventory(Commodity commodity, List<Inventory> inventorys,
			List<CategoryPropertiesVal> categoryPropertiesVals, SysMessage sysMessage) {

		int id = commodityMapper.insertCommodity(commodity);

		if (Utils.isObjectNotEmpty(commodity.getCommodityDetails().getInfo())) {
			commodity.getCommodityDetails().setCid(commodity.getId());
			commodityDetailsMapper.insertCommodityDetails(commodity.getCommodityDetails());
		}

		saveInventory(commodity, inventorys, categoryPropertiesVals, sysMessage);

		return id;
	}

	/***
	 * 库存操作
	 * 
	 * @param commodity
	 * @param inventorys
	 * @param categoryPropertiesVals
	 * @param sysMessage
	 */
	private void saveInventory(Commodity commodity, List<Inventory> inventorys,
			List<CategoryPropertiesVal> categoryPropertiesVals, SysMessage sysMessage) {
		
		
		Map<String, List<String>> map = new TreeMap<String, List<String>>();
		for (CategoryPropertiesVal categoryPropertiesVal : categoryPropertiesVals) {
			categoryPropertiesVal.setCid(commodity.getId());
			categoryPropertiesValMapper.insertCategoryPropertiesVal(categoryPropertiesVal);

			String cid = String.valueOf(categoryPropertiesVal.getId());
			String cpid = String.valueOf(categoryPropertiesVal.getCpid());
			if (map.get(cpid) == null) {
				List<String> list = new ArrayList<String>();
				list.add(cid);
				map.put(cpid, list);
			} else {
				map.get(cpid).add(cid);
			}
		}

		int attr_len = map.size();
		String[][] array = new String[map.size()][attr_len];
		int i = 0;
		for (List<String> s : map.values()) {
			array[i] = s.toArray(new String[s.size()]);
			i++;
		}

		List<String[]> skuAttr = Cartesian.cartesian(array);
		// if (skuAttr.size() != inventorys.size()) {
		// sysMessage.setCode("-102");
		// String msg = "生成sku数量与库存集合数量不等";
		// sysMessage.setMsg(msg);
		// throw new RuntimeException(msg);
		// }

		int index = 0;
		for (Inventory inventory : inventorys) {
			String[] sku = index < skuAttr.size() ? skuAttr.get(index) : null;
			inventory.setCid(commodity.getId());
			int attr1 = Utils.strToInteger(Utils.getArrayVal(0, sku));
			int attr2 = Utils.strToInteger(Utils.getArrayVal(1, sku));
			inventory.setAttr1(attr1 == 0 || attr2 == 0 ? attr1 : Math.min(attr1, attr2));
			inventory.setAttr2(attr1 == 0 || attr2 == 0 ? attr2 : Math.max(attr1, attr2));
			index++;
		}

		if (inventorys != null && inventorys.size() != 0) {
			inventoryMapper.insertBatchInventory(inventorys);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public Integer updateInventory(Commodity commodity, List<Inventory> inventorys,
			List<CategoryPropertiesVal> categoryPropertiesVals, SysMessage sysMessage) {

		if (commodity.getId() <= 0) {
			sysMessage.setMsg("商品id不合法 不能更新商品");
			throw new RuntimeException(sysMessage.getMsg());
		}

		categoryPropertiesValMapper.deleteCategoryPropertiesValByCid(commodity.getId());

		inventoryMapper.deleteInventoryByCid(commodity.getId());

		int id = commodityMapper.updateCommodity(commodity);

		if (Utils.isObjectNotEmpty(commodity.getCommodityDetails().getInfo())) {
			commodity.getCommodityDetails().setCid(commodity.getId());
			commodityDetailsMapper.updateCommodityDetailsByCid(commodity.getCommodityDetails());
		}

		saveInventory(commodity, inventorys, categoryPropertiesVals, sysMessage);

		return id;
	}

}
