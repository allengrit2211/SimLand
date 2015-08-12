package com.simland.core.module.purview.service;

import java.util.List;
import java.util.Map;

import com.simland.core.module.purview.entity.RolePower;

@SuppressWarnings("unchecked")
public interface IRolePowerService {
	
	public Integer insertRolePower(RolePower rolePower);

	public Integer updateRolePower(RolePower rolePower);

	public Integer deleteRolePower(Integer id);
	
	public RolePower getRolePower(Map param);
	
	public List<RolePower> getRolePowerList(Map param);

	public Integer getRolePowerCount(Map param);
	
	public List<RolePower> getSplitRolePowerList(Map param);
	
}
