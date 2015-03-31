package hu.bme.aut.vshelter.dal.impl;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.persistence.TypedQuery;

import hu.bme.aut.vshelter.dal.AddressFacade;
import hu.bme.aut.vshelter.entity.Address;


public class AddressFacadeJPAImpl implements AddressFacade {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public Address findAddressById(int addressId) {
		return em.find(Address.class, addressId);
	}

	@Override
	@Transactional
	public List<Address> findAll() {
		TypedQuery<Address> query = em.createQuery("SELECT a FROM Address a",
				Address.class);

		return query.getResultList();
	}

	@Override
	@Transactional
	public void create(Address address) {
		em.persist(address);
	}

	@Override
	@Transactional
	public void edit(Address address) {
		em.merge(address);
	}

	@Override
	@Transactional
	public void deleteAddressById(int addressId) {
		Query deleteQuery = em.createQuery(
				"DELETE FROM Address where id=:p")
				.setParameter("p", addressId);
		deleteQuery.executeUpdate();
	}

}
