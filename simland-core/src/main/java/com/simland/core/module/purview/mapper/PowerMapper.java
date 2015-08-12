package com.simland.core.module.purview.mapper;

import java.util.List;
import java.util.Map;

import com.simland.core.module.purview.entity.Power;

public interface PowerMapper{
	
	public Integer insertPower(Power power);  	
	
	public Integer updatePower(Power power);
	
	public Integer deletePower(Integer id);

	public Power getPower(Map param);
	
	public List getPowerList(Map param);

	public Integer getPowerCount(Map param);
	
	public List getSplitPowerList(Map param);

	public List<Power> getPowerListByRid(Integer rid);

}
