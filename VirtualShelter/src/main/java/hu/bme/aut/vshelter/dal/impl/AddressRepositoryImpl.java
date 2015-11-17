package hu.bme.aut.vshelter.dal.impl;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import javax.persistence.TypedQuery;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.dal.AddressFacade;
import hu.bme.aut.vshelter.dal.AddressRepositoryCustom;
import hu.bme.aut.vshelter.entity.Address;


public class AddressRepositoryImpl implements AddressRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;
	
	public AddressRepositoryImpl(EntityManager em) {
		super();
		this.em = em;
	}
	
	public AddressRepositoryImpl() {
		super();
	}

	@Override
	@Transactional
	public void edit(Address address) throws VirtualShelterException {
		try {
			em.merge(address);
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		} catch (TransactionRequiredException e) {
			throw new VirtualShelterException(e);
		} catch (ValidationException e) {
			throw new VirtualShelterException(e);
		} 
	}


}
