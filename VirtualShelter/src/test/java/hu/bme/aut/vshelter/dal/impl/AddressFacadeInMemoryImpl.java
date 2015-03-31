package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.entity.Address;
import hu.bme.aut.vshelter.dal.AddressFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddressFacadeInMemoryImpl implements AddressFacade {
	
	private List<Address> addresses = new ArrayList<Address>();

	@Override
	public Address findAddressById(int addressId) {
		for(Address addr : addresses)
			if(addr.getId() == addressId){
				return addr;
			}
		return null;
	}

	@Override
	public List<Address> findAll() {
		return Collections.unmodifiableList(addresses);
	}

	@Override
	public void create(Address address) {
		addresses.add(address);
	}

	@Override
	public void edit(Address address) {
		addresses.set(addresses.indexOf(address), address);
	}

	@Override
	public void deleteAddressById(int addressId) {
		Address deleteAddress = findAddressById(addressId);

		if(deleteAddress != null)
			addresses.remove(deleteAddress);
	}

}
