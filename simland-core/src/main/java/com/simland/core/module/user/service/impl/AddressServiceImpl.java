package com.simland.core.module.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simland.core.module.user.entity.Address;
import com.simland.core.module.user.mapper.AddressMapper;
import com.simland.core.module.user.service.IAddressService;

@Service("addressService")
@Transactional(rollbackFor = java.lang.Exception.class)
public class AddressServiceImpl implements IAddressService {

	@Autowired
	private AddressMapper addressMapper;

	public Integer insertAddress(Address address) {
		return addressMapper.insertAddress(address);
	}

	public Integer updateAddress(Address address) {
		return addressMapper.updateAddress(address);
	}

	public Integer deleteAddress(Integer id) {
		return addressMapper.deleteAddress(id);
	}

	public Address getAddress(Map param) {
		return (Address) addressMapper.getAddress(param);
	}

	public List<Address> getAddressList(Map param) {
		return addressMapper.getAddressList(param);
	}

	public Integer getAddressCount(Map param) {
		return (Integer) addressMapper.getAddressCount(param);
	}

	public List<Address> getSplitAddressList(Map param) {
		return addressMapper.getSplitAddressList(param);
	}

	@Override
	public Address getUserDefaultAddress(Integer uid) {
		if (uid == null || uid == 0)
			return null;

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("uid", uid);
		param.put("isDefault", 1);

		List<Address> list = addressMapper.getAddressList(param);
		if (list != null && list.size() != 0) {
			param = null;
			return list.get(0);
		} else {
			param = null;
			return null;
		}

	}

	@Override
	public Address getAddress(Integer id,Integer uid) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("uid", uid);
		param.put("id", id);
		return addressMapper.getAddress(param);
		
	}

}
