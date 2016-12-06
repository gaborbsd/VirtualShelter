package hu.bme.aut.sportnetwork.dal;

import hu.bme.aut.sportnetwork.entity.Address;

public interface AddressDAO {
	
	Address findByCityAndAddress(String city, String address);

}
