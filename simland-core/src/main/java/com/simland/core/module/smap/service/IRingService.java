package com.simland.core.module.smap.service;

import java.util.List;
import java.util.Map;

import com.simland.core.module.smap.entity.Ring;

@SuppressWarnings("unchecked")
public interface IRingService {
	
	public Integer insertRing(Ring ring);

	public Integer updateRing(Ring ring);

	public Integer deleteRing(Integer id);
	
	public Ring getRing(Map param);
	
	public List<Ring> getRingList(Map param);

	public Integer getRingCount(Map param);
	
	public List<Ring> getSplitRingList(Map param);
	
}
