package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Address;

import java.util.List;

public interface AddressFacade {
	
	Address findAddressById(int addressId);
	
	List<Address> findAll();
	
	void create(Address Address);
	
	void edit(Address Address);
	
	void deleteAddressById(int addressId);

}
