package com.simland.core.module.smap.mapper;

import java.util.List;
import java.util.Map;

import com.simland.core.module.smap.entity.RingDetails;

public interface RingDetailsMapper{
	
	public Integer insertRingDetails(RingDetails ringDetails);  	
	
	public Integer updateRingDetails(RingDetails ringDetails);
	
	public Integer deleteRingDetails(Integer id);

	public RingDetails getRingDetails(Map param);
	
	public List getRingDetailsList(Map param);

	public Integer getRingDetailsCount(Map param);
	
	public List getSplitRingDetailsList(Map param);

}
