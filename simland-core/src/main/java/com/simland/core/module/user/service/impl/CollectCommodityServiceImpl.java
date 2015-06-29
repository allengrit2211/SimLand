package com.simland.core.module.user.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.simland.core.module.user.mapper.CollectCommodityMapper;
import com.simland.core.module.user.entity.CollectCommodity;
import com.simland.core.module.user.service.ICollectCommodityService;

@Service("collectCommodityService")
@Transactional(rollbackFor = java.lang.Exception.class)
public class CollectCommodityServiceImpl implements ICollectCommodityService{

	@Autowired
	private CollectCommodityMapper collectCommodityMapper;
	
	public Integer insertCollectCommodity(CollectCommodity collectCommodity) {
		return collectCommodityMapper.insertCollectCommodity(collectCommodity);
	}

	public Integer updateCollectCommodity(CollectCommodity collectCommodity) {
		return collectCommodityMapper.updateCollectCommodity(collectCommodity);
	}
	
	public Integer deleteCollectCommodity(Integer id) {
		return collectCommodityMapper.deleteCollectCommodity(id);
	}
	
	public CollectCommodity getCollectCommodity(Map param) {
		return (CollectCommodity)collectCommodityMapper.getCollectCommodity(param);
	}
	
	public List<CollectCommodity> getCollectCommodityList(Map param) {
		return collectCommodityMapper.getCollectCommodityList(param);
	}
	
	public Integer getCollectCommodityCount(Map param) {
		return (Integer)collectCommodityMapper.getCollectCommodityCount(param);
	}
	
	public List<CollectCommodity> getSplitCollectCommodityList(Map param) {
		return collectCommodityMapper.getSplitCollectCommodityList(param);
	}
	
}
