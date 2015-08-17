package com.simland.core.module.smap.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.simland.core.module.smap.mapper.RingMapper;
import com.simland.core.module.smap.entity.Ring;
import com.simland.core.module.smap.service.IRingService;

@Service("ringService")
@Transactional(readOnly=true)
public class RingServiceImpl implements IRingService{

	@Autowired
	private RingMapper ringMapper;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer insertRing(Ring ring) {
		return ringMapper.insertRing(ring);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer updateRing(Ring ring) {
		return ringMapper.updateRing(ring);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer deleteRing(Integer id) {
		return ringMapper.deleteRing(id);
	}
	
	public Ring getRing(Map param) {
		return (Ring)ringMapper.getRing(param);
	}
	
	public List<Ring> getRingList(Map param) {
		return ringMapper.getRingList(param);
	}
	
	public Integer getRingCount(Map param) {
		return (Integer)ringMapper.getRingCount(param);
	}
	
	public List<Ring> getSplitRingList(Map param) {
		return ringMapper.getSplitRingList(param);
	}
	
}
