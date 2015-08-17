package com.simland.core.module.shop.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.simland.core.module.shop.mapper.WaitOrderMapper;
import com.simland.core.module.shop.entity.WaitOrder;
import com.simland.core.module.shop.service.IWaitOrderService;

@Service("waitOrderService")
@Transactional(readOnly=true)
public class WaitOrderServiceImpl implements IWaitOrderService {

	@Autowired
	private WaitOrderMapper waitOrderMapper;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Integer insertWaitOrder(WaitOrder waitOrder) {
		return waitOrderMapper.insertWaitOrder(waitOrder);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Integer updateWaitOrder(WaitOrder waitOrder) {
		return waitOrderMapper.updateWaitOrder(waitOrder);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Integer deleteWaitOrder(Integer id) {
		return waitOrderMapper.deleteWaitOrder(id);
	}

	public WaitOrder getWaitOrder(Map param) {
		return (WaitOrder) waitOrderMapper.getWaitOrder(param);
	}

	public List<WaitOrder> getWaitOrderList(Map param) {
		return waitOrderMapper.getWaitOrderList(param);
	}

	public Integer getWaitOrderCount(Map param) {
		return (Integer) waitOrderMapper.getWaitOrderCount(param);
	}

	public List<WaitOrder> getSplitWaitOrderList(Map param) {
		return waitOrderMapper.getSplitWaitOrderList(param);
	}

}
