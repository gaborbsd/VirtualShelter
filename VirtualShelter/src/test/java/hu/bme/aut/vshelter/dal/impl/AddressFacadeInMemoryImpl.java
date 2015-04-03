package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.entity.Address;
import hu.bme.aut.vshelter.dal.AddressFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddressFacadeInMemoryImpl implements AddressFacade {
	
	private List<Address> addresses = new ArrayList<Address>();

	@Override
	public Address findAddressById(long addressId) {
		return addresses.stream().filter(a -> a.getId() == addressId)
				.findFirst().get();
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
	public void deleteAddressById(long addressId) {
		Address deleteAddress = findAddressById(addressId);

		if(deleteAddress != null)
			addresses.remove(deleteAddress);
	}

}
