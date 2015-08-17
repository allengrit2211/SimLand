package com.simland.core.module.purview.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.simland.core.base.Utils;
import com.simland.core.module.purview.entity.Role;
import com.simland.core.module.purview.entity.RolePower;
import com.simland.core.module.purview.mapper.RoleMapper;
import com.simland.core.module.purview.mapper.RolePowerMapper;
import com.simland.core.module.purview.service.IRoleService;

@Service("roleService")
@Transactional(readOnly=true)
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private RolePowerMapper rolePowerMapper;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Integer insertRole(Role role) {
		return roleMapper.insertRole(role);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Integer updateRole(Role role) {
		return roleMapper.updateRole(role);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Integer deleteRole(Integer id) {
		return roleMapper.deleteRole(id);
	}

	public Role getRole(Map param) {
		return (Role) roleMapper.getRole(param);
	}

	public List<Role> getRoleList(Map param) {
		return roleMapper.getRoleList(param);
	}

	public Integer getRoleCount(Map param) {
		return (Integer) roleMapper.getRoleCount(param);
	}

	public List<Role> getSplitRoleList(Map param) {
		return roleMapper.getSplitRoleList(param);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Integer insertRole(Role role, List<Integer> powers) {
		int id = roleMapper.insertRole(role);
		if (Utils.isObjectEmpty(role.getId()))
			throw new RuntimeException("role insert exception");

		List<RolePower> list = new LinkedList<RolePower>();
		for (int i = 0; Utils.isObjectNotEmpty(powers) && i < powers.size(); i++) {
			if (Utils.isObjectEmpty(powers.get(i)))
				continue;
			list.add(new RolePower(role.getId(), powers.get(i)));
		}
		rolePowerMapper.insertBatchRolePower(list);
		return id;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Integer updateRole(Role role, List<Integer> powers) {

		if (Utils.isObjectEmpty(role) || Utils.isObjectEmpty(role.getId())) {
			return -1;
		}

		if (Utils.isObjectEmpty(powers) || powers.size() == 0)
			return -1;

		rolePowerMapper.delRolePowerByRid(role.getId());

		List<RolePower> list = new LinkedList<RolePower>();
		for (int i = 0; Utils.isObjectNotEmpty(powers) && i < powers.size(); i++) {
			if (Utils.isObjectEmpty(powers.get(i)))
				continue;
			list.add(new RolePower(role.getId(), powers.get(i)));
		}
		rolePowerMapper.insertBatchRolePower(list);

		return 1;
	}

}
