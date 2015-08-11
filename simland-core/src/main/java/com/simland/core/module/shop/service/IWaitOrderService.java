package com.simland.core.module.shop.service;

import java.util.List;
import java.util.Map;

import com.simland.core.module.shop.entity.WaitOrder;

@SuppressWarnings("unchecked")
public interface IWaitOrderService {
	
	public Integer insertWaitOrder(WaitOrder waitOrder);

	public Integer updateWaitOrder(WaitOrder waitOrder);

	public Integer deleteWaitOrder(Integer id);
	
	public WaitOrder getWaitOrder(Map param);
	
	public List<WaitOrder> getWaitOrderList(Map param);

	public Integer getWaitOrderCount(Map param);
	
	public List<WaitOrder> getSplitWaitOrderList(Map param);
	
}
