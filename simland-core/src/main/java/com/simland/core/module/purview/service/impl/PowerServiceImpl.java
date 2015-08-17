package com.simland.core.module.purview.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.simland.core.base.Utils;
import com.simland.core.module.purview.entity.Power;
import com.simland.core.module.purview.mapper.PowerMapper;
import com.simland.core.module.purview.service.IPowerService;

@Service("powerService")
@Transactional(readOnly=true)
public class PowerServiceImpl implements IPowerService {

	@Autowired
	private PowerMapper powerMapper;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Integer insertPower(Power power) {
		return powerMapper.insertPower(power);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Integer updatePower(Power power) {
		return powerMapper.updatePower(power);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Integer deletePower(Integer id) {
		return powerMapper.deletePower(id);
	}

	public Power getPower(Map param) {
		return (Power) powerMapper.getPower(param);
	}

	public List<Power> getPowerList(Map param) {
		return powerMapper.getPowerList(param);
	}

	public Integer getPowerCount(Map param) {
		return (Integer) powerMapper.getPowerCount(param);
	}

	public List<Power> getSplitPowerList(Map param) {
		return powerMapper.getSplitPowerList(param);
	}

	@Override
	public List<Power> getPowerRecursiveList(Map<String, Object> param) {

		if (Utils.isObjectEmpty(param))
			param = new HashMap<String, Object>();

		param.put("pid", 0);
		List<Power> root = powerMapper.getPowerList(param);
		for (int j = 0; Utils.isObjectNotEmpty(root) && j < root.size(); j++) {
			Power p = root.get(j);
			param.put("pid", p.getId());
			List<Power> node1 = null;
			p.setSubPowerList(node1 = powerMapper.getPowerList(param));
			for (int i = 0; Utils.isObjectNotEmpty(node1) && i < node1.size(); i++) {
				Power p1 = node1.get(i);
				param.put("pid", p1.getId());
				p1.setSubPowerList(powerMapper.getPowerList(param));
			}
		}
		return root;
	}

	@Override
	public List<Power> getPowerListByRid(Integer rid) {

		if (Utils.isObjectEmpty(rid) || rid == 0)
			return null;

		
		return powerMapper.getPowerListByRid(rid);
	}

}
