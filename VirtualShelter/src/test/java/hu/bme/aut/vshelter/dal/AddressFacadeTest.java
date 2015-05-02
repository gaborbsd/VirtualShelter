package hu.bme.aut.vshelter.dal;

import static org.junit.Assert.*;

import java.util.List;

import hu.bme.aut.vshelter.entity.Address;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-config.xml" })
public class AddressFacadeTest {

	@Autowired
	private AddressFacade addressFacade;
	
	
	@Test
	public void testPersistAddedIsContains() {
		Address address = new Address();
		address.setCountry("Magyarország");
		address.setCity("Budapest");
		address.setZipCode(1117);
		address.setAddress("Irinyi József utca");
		address.setId(1);
		address.setState("Pest");
		address.setProvince("province");
		address.setLatitude(47.473044);
		address.setLongitude(19.052765);
		addressFacade.create(address);
		List<Address> addresses = addressFacade.findAll();
		assertTrue(addresses.contains(address));
	}
	
	@Test
	public void testPersistNotAddedIsNotContains() {
		Address address = new Address();
		address.setCountry("Magyarország");
		address.setCity("1118");
		address.setAddress("Apostol József tér 9");
		address.setId(2);
		List<Address> addresses = addressFacade.findAll();
		assertFalse(addresses.contains(address));
	}
	
	@Test
	public void testFindAddressById(){
		Address address = new Address();
		Address actual;
		address.setCountry("Magyarors");
		address.setCity("Budape");
		address.setZipCode(1116);
		address.setAddress("József utca");
		address.setId(3);
		address.setState("hajdú");
		address.setProvince("adda");
		address.setLatitude(12.473044);
		address.setLongitude(18.052765);
		
		addressFacade.create(address);
		actual = addressFacade.findAddressById(3);
		assertEquals(address, actual);
		assertEquals(address.getCountry(), actual.getCountry());
		assertEquals(address.getCity(), actual.getCity());
		assertEquals(address.getZipCode(), actual.getZipCode());
		assertEquals(address.getAddress(), actual.getAddress());
		assertEquals(address.getId(), actual.getId());
		assertEquals(address.getState(), actual.getState());
		assertEquals(address.getProvince(), actual.getProvince());
		assertTrue(address.getLongitude() == actual.getLongitude());
		assertTrue(address.getLatitude() == actual.getLatitude());
		assertEquals(address.hashCode(), actual.hashCode());
	}
	
	@Test
	public void testNoFindAddressByNotExistId(){
		Address actual;
		actual = addressFacade.findAddressById(4031);
		assertTrue(actual == null);
	}
	
	@Test
	public void testEditAddress(){
		Address address = new Address();
		address.setCountry("Masgyar");
		address.setCity("3118");
		address.setAddress("Irinyi József utas");
		address.setId(5);
		addressFacade.create(address);
		
		Address modified = new Address();
		modified.setCountry("Anglia");
		modified.setCity("5555");
		modified.setAddress("alma");
		modified.setId(5);
		
		addressFacade.edit(modified);
		
		Address actual;
		actual = addressFacade.findAddressById(5);
		assertEquals(modified, actual);
		assertEquals(modified.getCountry(), actual.getCountry());
		assertEquals(modified.getCity(), actual.getCity());
		assertEquals(modified.getAddress(), actual.getAddress());
		assertEquals(modified.getId(), actual.getId());
	}
	
	@Test
	public void testDeleteAddressById(){
		Address address = new Address();
		address.setCountry("Alabama");
		address.setCity("2222");
		address.setAddress("Czitera ut");
		address.setId(6);
		addressFacade.create(address);
		
		addressFacade.deleteAddressById(6);
		
		Address actual;
		actual = addressFacade.findAddressById(6);
		assertTrue(actual == null);
	}
	
}
