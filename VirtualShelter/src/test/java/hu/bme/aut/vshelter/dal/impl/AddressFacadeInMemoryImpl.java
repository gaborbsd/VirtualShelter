package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.entity.Address;
import hu.bme.aut.vshelter.dal.AddressFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class AddressFacadeInMemoryImpl implements AddressFacade {
	
	private List<Address> addresses = new ArrayList<Address>();

	@Override
	public Address findAddressById(long addressId) {
		try{
			return addresses.stream().filter(a -> a.getId() == addressId)
					.findFirst().get();
		}catch(NoSuchElementException e){
			return null;
		}
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
		System.out.println(findAddressById(address.getId()).getId());
		addresses.set(addresses.indexOf(findAddressById(address.getId())), address);
	}

	@Override
	public void deleteAddressById(long addressId) {
		Address deleteAddress = findAddressById(addressId);

		if(deleteAddress != null)
			addresses.remove(deleteAddress);
	}

}
