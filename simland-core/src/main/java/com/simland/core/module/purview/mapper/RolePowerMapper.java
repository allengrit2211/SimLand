package com.simland.core.module.purview.mapper;

import java.util.List;
import java.util.Map;

import com.simland.core.module.purview.entity.RolePower;

public interface RolePowerMapper {

	public Integer insertRolePower(RolePower rolePower);

	public Integer insertBatchRolePower(List<RolePower> rolePowers);

	public Integer updateRolePower(RolePower rolePower);

	public Integer deleteRolePower(Integer id);

	public RolePower getRolePower(Map param);

	public List getRolePowerList(Map param);

	public Integer getRolePowerCount(Map param);

	public List getSplitRolePowerList(Map param);

	public Integer delRolePowerByRid(Integer rid);

}
