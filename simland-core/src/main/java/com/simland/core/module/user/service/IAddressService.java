package com.simland.core.module.user.service;

import java.util.List;
import java.util.Map;

import com.simland.core.module.user.entity.Address;

public interface IAddressService {

	public Integer insertAddress(Address address);

	public Integer updateAddress(Address address);

	public Integer deleteAddress(Integer id);

	public Address getAddress(Map param);

	public Address getUserDefaultAddress(Integer uid);

	public List<Address> getAddressList(Map param);

	public Integer getAddressCount(Map param);

	public List<Address> getSplitAddressList(Map param);

}
