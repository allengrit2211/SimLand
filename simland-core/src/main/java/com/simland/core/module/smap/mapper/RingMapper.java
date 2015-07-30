package com.simland.core.module.smap.mapper;

import java.util.List;
import java.util.Map;

import com.simland.core.module.smap.entity.Ring;

public interface RingMapper{
	
	public Integer insertRing(Ring ring);  	
	
	public Integer updateRing(Ring ring);
	
	public Integer deleteRing(Integer id);

	public Ring getRing(Map param);
	
	public List getRingList(Map param);

	public Integer getRingCount(Map param);
	
	public List getSplitRingList(Map param);

}
