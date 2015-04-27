package hu.bme.aut.vshelter.dal;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.vshelter.dal.impl.AddressFacadeJPAImpl;
import hu.bme.aut.vshelter.entity.Address;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class AddressFacadeMockTest {

	private AddressFacadeJPAImpl addressFacade;
	private EntityManager mockEm;
	private TypedQuery<Address> mockQuery;
	
	  @Before
	  @SuppressWarnings("unchecked")
	  public void setUp() throws Exception {
	    // NiceMocks return default values for
	    // unimplemented methods
		mockEm = createNiceMock(EntityManager.class);
		mockQuery = (TypedQuery<Address>)createMock(TypedQuery.class);
	    addressFacade = new AddressFacadeJPAImpl(mockEm);
	  }

	  @Test
	  public void findAddressByIdTest() {
	    
		Address address = new Address();
		address.setId(1);
		address.setAddress("alma street");
		address.setCity("Nagyvárad");
		address.setCountry("Hungary");
		
		// Setting up the expected value of the method call find
	    expect(mockEm.find(Address.class, address.getId())).andReturn(address).times(1);
	    
	    // Setup is finished need to activate the mock
	    replay(mockEm);
	    
	    addressFacade.create(address); // add an address 
	    assertEquals(address, addressFacade.findAddressById(address.getId()));

	    verify(mockEm);
	  }
	  
	 
	  @Test
	  public void findAllAnimalTest() {
		Address address = new Address();
		address.setId(1);
		address.setAddress("alma street");
		address.setCity("Nagyvárad");
		address.setCountry("Hungary");
		List<Address> addresses = new ArrayList<Address>();
		addresses.add(address);
		
		// Setting up the expected value of the method createQuery and getResultList
		expect(mockEm.createQuery("SELECT a FROM Address a",
				Address.class)).andReturn(mockQuery);
		expect(mockQuery.getResultList()).andReturn(addresses);
		
		// Setup is finished need to activate the mock
	    replay(mockEm);
	    replay(mockQuery);
	    
	    addressFacade.create(address); // add an address
	    assertEquals(addresses, addressFacade.findAll());
	    
	    verify(mockEm);
	    verify(mockQuery);
	  }
	
}
