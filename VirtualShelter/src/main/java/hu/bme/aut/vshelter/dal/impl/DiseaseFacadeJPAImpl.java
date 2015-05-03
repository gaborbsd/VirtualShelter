package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.dal.DiseaseFacade;
import hu.bme.aut.vshelter.entity.Disease;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.transaction.Transactional;
import javax.persistence.TypedQuery;

public class DiseaseFacadeJPAImpl implements DiseaseFacade {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public Disease findById(long diseaseId) throws VirtualShelterException {
		try {
			return em.find(Disease.class, diseaseId);
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		}
	}

	@Override
	@Transactional
	public List<Disease> findAll() throws VirtualShelterException {
		try {
			TypedQuery<Disease> query = em.createQuery("SELECT d FROM Disease d",
					Disease.class);
	
			return query.getResultList();
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		}
	}

	@Override
	@Transactional
	public void create(Disease disease) throws VirtualShelterException {
		try {
			em.persist(disease);
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
	public void edit(Disease disease) throws VirtualShelterException {
		try {
			em.merge(disease);
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		} catch (TransactionRequiredException e) {
			throw new VirtualShelterException(e);
		}
	}

	@Override
	@Transactional
	public void deleteById(long diseaseId) throws VirtualShelterException {
		try {
			Query deleteQuery = em.createQuery(
					"DELETE FROM Disease where id=:p")
					.setParameter("p", diseaseId);
			deleteQuery.executeUpdate();
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		}
	}

}
