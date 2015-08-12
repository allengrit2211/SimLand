package com.simland.core.module.purview.service;

import java.util.List;
import java.util.Map;

import com.simland.core.module.purview.entity.Role;

public interface IRoleService {

	public Integer insertRole(Role role);

	public Integer insertRole(Role role, List<Integer> powers);

	public Integer updateRole(Role role);

	public Integer deleteRole(Integer id);

	public Role getRole(Map param);

	public List<Role> getRoleList(Map param);

	public Integer getRoleCount(Map param);

	public List<Role> getSplitRoleList(Map param);

	public Integer updateRole(Role role, List<Integer> powers);

}
