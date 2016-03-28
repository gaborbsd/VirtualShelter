package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.dal.AddressFacade;
import hu.bme.aut.vshelter.entity.Address;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class AddressFacadeInMemoryImpl implements AddressFacade {

    private List<Address> addresses = new ArrayList<Address>();

    @Override
    public Address findById(long addressId) throws VirtualShelterException {
        try {
            return addresses.stream().filter(a -> a.getId() == addressId)
                    .findFirst().get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public List<Address> findAll() throws VirtualShelterException {
        return Collections.unmodifiableList(addresses);
    }

    @Override
    public void create(Address address) throws VirtualShelterException {
        addresses.add(address);
    }

    @Override
    public void edit(Address address) throws VirtualShelterException {
        System.out.println(findById(address.getId()).getId());
        addresses.set(addresses.indexOf(findById(address.getId())), address);
    }

    @Override
    public void deleteById(long addressId) throws VirtualShelterException {
        Address deleteAddress = findById(addressId);

        if (deleteAddress != null)
            addresses.remove(deleteAddress);
    }

}
