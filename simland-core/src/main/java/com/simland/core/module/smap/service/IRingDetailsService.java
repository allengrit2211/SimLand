package com.simland.core.module.smap.service;

import java.util.List;
import java.util.Map;

import com.simland.core.module.smap.entity.RingDetails;

@SuppressWarnings("unchecked")
public interface IRingDetailsService {
	
	public Integer insertRingDetails(RingDetails ringDetails);

	public Integer updateRingDetails(RingDetails ringDetails);

	public Integer deleteRingDetails(Integer id);
	
	public RingDetails getRingDetails(Map param);
	
	public List<RingDetails> getRingDetailsList(Map param);

	public Integer getRingDetailsCount(Map param);
	
	public List<RingDetails> getSplitRingDetailsList(Map param);
	
}
