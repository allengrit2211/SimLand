package com.simland.core.module.purview.service;

import java.util.List;
import java.util.Map;

import com.simland.core.module.purview.entity.Power;

public interface IPowerService {

	public Integer insertPower(Power power);

	public Integer updatePower(Power power);

	public Integer deletePower(Integer id);

	public Power getPower(Map param);

	public List<Power> getPowerList(Map param);

	public List<Power> getPowerRecursiveList(Map<String, Object> param);

	public Integer getPowerCount(Map param);

	public List<Power> getSplitPowerList(Map param);

	/***
	 * 根绝角色ID 获取权限列表
	 * 
	 * @param param
	 * @return
	 */
	public List<Power> getPowerListByRid(Integer rid);

}
