package com.simland.core.module.smap.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.simland.core.module.smap.mapper.RingDetailsMapper;
import com.simland.core.module.smap.entity.RingDetails;
import com.simland.core.module.smap.service.IRingDetailsService;

@Service("ringDetailsService")
@Transactional(rollbackFor = java.lang.Exception.class)
public class RingDetailsServiceImpl implements IRingDetailsService{

	@Autowired
	private RingDetailsMapper ringDetailsMapper;
	
	public Integer insertRingDetails(RingDetails ringDetails) {
		return ringDetailsMapper.insertRingDetails(ringDetails);
	}

	public Integer updateRingDetails(RingDetails ringDetails) {
		return ringDetailsMapper.updateRingDetails(ringDetails);
	}
	
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
