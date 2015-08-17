package com.simland.core.module.smap.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.simland.core.module.smap.mapper.RingDetailsMapper;
import com.simland.core.module.smap.entity.RingDetails;
import com.simland.core.module.smap.service.IRingDetailsService;

@Service("ringDetailsService")
@Transactional(readOnly=true)
public class RingDetailsServiceImpl implements IRingDetailsService{

	@Autowired
	private RingDetailsMapper ringDetailsMapper;

	@Transactional(propagation = Propagation.REQUIRED)
	public Integer insertRingDetails(RingDetails ringDetails) {
		return ringDetailsMapper.insertRingDetails(ringDetails);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Integer updateRingDetails(RingDetails ringDetails) {
		return ringDetailsMapper.updateRingDetails(ringDetails);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer deleteRingDetails(Integer id) {
		return ringDetailsMapper.deleteRingDetails(id);
	}
	
	public RingDetails getRingDetails(Map param) {
		return (RingDetails)ringDetailsMapper.getRingDetails(param);
	}
	
	public List<RingDetails> getRingDetailsList(Map param) {
		return ringDetailsMapper.getRingDetailsList(param);
	}
	
	public Integer getRingDetailsCount(Map param) {
		return (Integer)ringDetailsMapper.getRingDetailsCount(param);
	}
	
	public List<RingDetails> getSplitRingDetailsList(Map param) {
		return ringDetailsMapper.getSplitRingDetailsList(param);
	}
	
}
