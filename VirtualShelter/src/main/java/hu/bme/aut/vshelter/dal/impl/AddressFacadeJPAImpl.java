package hu.bme.aut.vshelter.dal.impl;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.transaction.Transactional;
import javax.persistence.TypedQuery;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.dal.AddressFacade;
import hu.bme.aut.vshelter.entity.Address;


public class AddressFacadeJPAImpl implements AddressFacade {
	
	@PersistenceContext
	private EntityManager em;
	
	public AddressFacadeJPAImpl(EntityManager em) {
		super();
		this.em = em;
	}
	
	public AddressFacadeJPAImpl() {
		super();
	}
	
	@Override
	@Transactional
	public Address findById(long addressId) throws VirtualShelterException {
		try {
			return em.find(Address.class, addressId);
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		}
	}

	@Override
	@Transactional
	public List<Address> findAll() throws VirtualShelterException {
		try {
			TypedQuery<Address> query = em.createQuery("SELECT a FROM Address a",
					Address.class);
			return query.getResultList();
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		}

		
	}

	@Override
	@Transactional
	public void create(Address address) throws VirtualShelterException {
		try {
			em.persist(address);
		} catch (EntityExistsException e) {
			throw new VirtualShelterException(e);
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		} catch (TransactionRequiredException e) {
			throw new VirtualShelterException(e);
		}
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
		}
	}

	@Override
	@Transactional
	public void deleteById(long addressId) throws VirtualShelterException {
		try {
			Query deleteQuery = em.createQuery(
					"DELETE FROM Address where id=:p")
					.setParameter("p", addressId);
			deleteQuery.executeUpdate();
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		}
	}

}
