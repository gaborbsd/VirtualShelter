package hu.bme.aut.vshelter.dal;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.vshelter.dal.impl.AddressFacadeJPAImpl;
import hu.bme.aut.vshelter.entity.Address;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;

public class AddressFacadeMockTest {

	private AddressFacadeJPAImpl addressFacade;
	private EntityManager mockEm;
	private TypedQuery<Address> mockTypedQuery;
	private Query mockQuery;

	@Before
	@SuppressWarnings("unchecked")
	public void setUp() throws Exception {
		mockEm = createMock(EntityManager.class);
		mockTypedQuery = (TypedQuery<Address>) createMock(TypedQuery.class);
		mockQuery = createMock(Query.class);
		addressFacade = new AddressFacadeJPAImpl(mockEm);
	}

	@Test
	public void findAddressByIdShouldReturnAddressTest() {
		Address address = new Address();
		address.setId(1);
		address.setAddress("alma street");
		address.setCity("Nagyvárad");
		address.setCountry("Hungary");

		mockEm.persist(address);
		expectLastCall();
		expect(mockEm.find(Address.class, address.getId())).andReturn(address);

		replay(mockEm);

		addressFacade.create(address);
		assertEquals(address, addressFacade.findById(address.getId()));

		verify(mockEm);
	}

	@Test
	public void findAddressByIdShouldReturnNullTest() {
		expect(mockEm.find(Address.class, (long) 1)).andReturn(null);

		replay(mockEm);

		assertEquals(null, addressFacade.findById((long) 1));

		verify(mockEm);
	}

	@Test
	public void findAllAddressShouldReturnAllAddressTest() {
		Address address = new Address();
		address.setId(2);
		address.setAddress("alma street");
		address.setCity("Nagyvárad");
		address.setCountry("Hungary");
		List<Address> addresses = new ArrayList<Address>();
		addresses.add(address);

		mockEm.persist(address);
		expectLastCall();
		expect(mockEm.createQuery("SELECT a FROM Address a", Address.class))
				.andReturn(mockTypedQuery);
		expect(mockTypedQuery.getResultList()).andReturn(addresses);

		replay(mockEm);
		replay(mockTypedQuery);

		addressFacade.create(address);
		assertEquals(addresses, addressFacade.findAll());

		verify(mockEm);
		verify(mockTypedQuery);
	}

	@Test
	public void findAllAddressShouldReturnNullTest() {
		expect(mockEm.createQuery("SELECT a FROM Address a", Address.class))
				.andReturn(mockTypedQuery);
		expect(mockTypedQuery.getResultList()).andReturn(null);

		replay(mockEm);
		replay(mockTypedQuery);

		assertEquals(null, addressFacade.findAll());

		verify(mockEm);
		verify(mockTypedQuery);
	}

	@Test
	public void createAddressTest() {
		Address address = new Address();
		address.setId(3);
		address.setAddress("alma street");
		address.setCity("Nagyvárad");
		address.setCountry("Hungary");

		mockEm.persist(address);
		expectLastCall();
		replay(mockEm);

		addressFacade.create(address);
		verify(mockEm);
	}

	@Test
	public void editAddressShouldModifieorCreateAddressTest() {
		Address address = new Address();
		address.setId(3);
		address.setAddress("alma street");
		address.setCity("Nagyvárad");
		address.setCountry("Hungary");

		expect(mockEm.merge(address)).andReturn(address);
		replay(mockEm);

		addressFacade.edit(address);
		verify(mockEm);
	}

	@Test
	public void deletAddressByIdDeleteTheAddress() {
		Address address = new Address();
		address.setId(6);
		address.setAddress("alma street");
		address.setCity("Nagyvárad");
		address.setCountry("Hungary");
		List<Address> addresses = new ArrayList<Address>();
		addresses.add(address);

		mockEm.persist(address);
		expectLastCall();

		expect(mockEm.createQuery("DELETE FROM Address where id=:p"))
				.andReturn(mockQuery);
		expect(mockQuery.setParameter("p", address.getId()))
				.andReturn(mockQuery);

		expect(mockQuery.executeUpdate()).andReturn(1);

		expect(mockEm.createQuery("SELECT a FROM Address a", Address.class))
				.andReturn(mockTypedQuery);
		expect(mockTypedQuery.getResultList()).andReturn(null);

		replay(mockEm);
		replay(mockQuery);
		replay(mockTypedQuery);

		addressFacade.create(address);
		
		addressFacade.deleteById(address.getId());
		
		assertEquals(null, addressFacade.findAll());

		verify(mockEm);
		verify(mockQuery);
		verify(mockTypedQuery);
	}
}
