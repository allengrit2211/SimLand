package com.simland.core.module.purview.mapper;

import java.util.List;
import java.util.Map;

import com.simland.core.module.purview.entity.Role;

public interface RoleMapper{
	
	public Integer insertRole(Role role);  	
	
	public Integer updateRole(Role role);
	
	public Integer deleteRole(Integer id);

	public Role getRole(Map param);
	
	public List getRoleList(Map param);

	public Integer getRoleCount(Map param);
	
	public List getSplitRoleList(Map param);

}
