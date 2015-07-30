package com.simland.core.module.user.mapper;

import java.util.List;
import java.util.Map;

import com.simland.core.module.user.entity.Address;

public interface AddressMapper{
	
	public Integer insertAddress(Address address);  	
	
	public Integer updateAddress(Map param);
	
	public Integer updateAddress(Address address);
	
	public Integer deleteAddress(Integer id);

	public Address getAddress(Map param);
	
	public List getAddressList(Map param);

	public Integer getAddressCount(Map param);
	
	public List getSplitAddressList(Map param);
	
	public Integer updateAddressByUserId(Map param);

}
