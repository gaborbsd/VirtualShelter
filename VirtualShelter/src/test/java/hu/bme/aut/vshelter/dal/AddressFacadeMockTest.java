package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.dal.impl.AddressFacadeJPAImpl;
import hu.bme.aut.vshelter.entity.Address;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

        try {
            addressFacade.create(address);
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }
        try {
            assertEquals(address, addressFacade.findById(address.getId()));
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

        verify(mockEm);
    }

    @Test
    public void findAddressByIdShouldReturnNullTest() {
        expect(mockEm.find(Address.class, (long) 1)).andReturn(null);

        replay(mockEm);

        try {
            assertEquals(null, addressFacade.findById((long) 1));
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

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

        try {
            addressFacade.create(address);
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }
        try {
            assertEquals(addresses, addressFacade.findAll());
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

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

        try {
            assertEquals(null, addressFacade.findAll());
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

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

        try {
            addressFacade.create(address);
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }
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

        try {
            addressFacade.edit(address);
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }
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

        try {
            addressFacade.create(address);
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

        try {
            addressFacade.deleteById(address.getId());
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

        try {
            assertEquals(null, addressFacade.findAll());
        } catch (VirtualShelterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("VirtualShelterException dobódott" + e.getMessage());
        }

        verify(mockEm);
        verify(mockQuery);
        verify(mockTypedQuery);
    }
}
