package com.simland.core.module.shop.mapper;

import java.util.List;
import java.util.Map;

import com.simland.core.module.shop.entity.WaitOrder;

public interface WaitOrderMapper{
	
	public Integer insertWaitOrder(WaitOrder waitOrder);  	
	
	public Integer updateWaitOrder(WaitOrder waitOrder);
	
	public Integer deleteWaitOrder(Integer id);

	public WaitOrder getWaitOrder(Map param);
	
	public List getWaitOrderList(Map param);

	public Integer getWaitOrderCount(Map param);
	
	public List getSplitWaitOrderList(Map param);

}
