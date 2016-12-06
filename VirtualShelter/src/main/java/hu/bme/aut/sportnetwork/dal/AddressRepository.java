package hu.bme.aut.sportnetwork.dal;

import org.springframework.data.neo4j.repository.GraphRepository;

import hu.bme.aut.sportnetwork.entity.Address;

public interface AddressRepository extends GraphRepository<Address> {

	Address findByCountryAndCityAndAddress(String country, String city, String address);

}
