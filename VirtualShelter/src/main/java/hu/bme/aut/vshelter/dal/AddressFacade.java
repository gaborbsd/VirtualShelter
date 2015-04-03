package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Address;

import java.util.List;

public interface AddressFacade {
	
	Address findAddressById(long addressId);
	
	List<Address> findAll();
	
	void create(Address address);
	
	void edit(Address address);
	
	void deleteAddressById(long addressId);

}
