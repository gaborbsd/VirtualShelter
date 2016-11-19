package hu.bme.aut.sportnetwork.dal.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import hu.bme.aut.sportnetwork.dal.SportEventDAOCustom;
import hu.bme.aut.sportnetwork.entity.Address;
import hu.bme.aut.sportnetwork.entity.SportEvent;

public class SportEventDAOImpl implements SportEventDAOCustom {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public SportEvent saveNewEvent(SportEvent e) {
		TypedQuery<Address> addressQuery = em.createQuery("SELECT a FROM Address a WHERE a.city=:city AND a.address=:address", Address.class);
		addressQuery.setParameter("city", e.getAddress().getCity());
		addressQuery.setParameter("address", e.getAddress().getAddress());
		
		Address a = null;
		try {
			a = addressQuery.getSingleResult();
		} catch (NoResultException ex) {
			
		}
		
		SportEvent ret = null;
		
		if (a != null) {
			e.setAddress(a);
			ret = em.merge(e);
		} else {
			em.persist(e);
			ret = e;
		}
		
		return ret;
	}

}
