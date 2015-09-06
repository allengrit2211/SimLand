package com.simland.core.module.shop.service;

import java.util.List;
import java.util.Map;

import com.simland.core.base.SysMessage;
import com.simland.core.module.shop.entity.CategoryPropertiesVal;
import com.simland.core.module.shop.entity.Commodity;
import com.simland.core.module.shop.entity.Inventory;

public interface IInventoryService {

	public Integer insertInventory(Inventory inventory);

	public Integer insertInventory(Commodity commodity, List<Inventory> inventorys,
			List<CategoryPropertiesVal> categoryPropertiesVals, SysMessage sysMessage);

	public Integer updateInventory(Inventory inventory);

	public Integer updateInventory(Commodity commodity, List<Inventory> inventorys,
			List<CategoryPropertiesVal> categoryPropertiesVals, SysMessage sysMessage);

	public Integer deleteInventory(Integer id);

	public Inventory getInventory(Map param);

	public List<Inventory> getInventoryList(Map param);

	public Integer getInventoryCount(Map param);

	public List<Inventory> getSplitInventoryList(Map param);

	/***
	 * 批量更新库存数量
	 * 
	 * @param param
	 *            #nums #id
	 */
	public void updateInventoryNums(Integer id, Integer nums);

	/***
	 * 
	 * @param cid
	 *            商品ID
	 * @param sid
	 *            商家ID
	 * @param attrVal1Id
	 *            属性值1ID
	 * @param attrVal2Id
	 *            属性值2ID
	 * @return
	 */
	public Inventory getInventoryNumsBySku(Integer cid, Integer sid, Integer attrVal1Id, Integer attrVal2Id);

}
