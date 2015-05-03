package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.dal.InstitutionFacade;
import hu.bme.aut.vshelter.entity.Institution;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.transaction.Transactional;
import javax.persistence.TypedQuery;

public class InstitutionFacadeJPAImpl implements InstitutionFacade {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public Institution findById(long institutionId) throws VirtualShelterException {
		try {
			return em.find(Institution.class, institutionId);
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		} 
	}

	@Override
	@Transactional
	public List<Institution> findAll() throws VirtualShelterException {
		try {
			TypedQuery<Institution> query = em.createQuery("SELECT i FROM Institution i",
					Institution.class);
	
			return query.getResultList();
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		} 
	}

	@Override
	@Transactional
	public void create(Institution institution) throws VirtualShelterException {
		try {
			em.persist(institution);
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
	public void edit(Institution institution) throws VirtualShelterException {
		try {
			em.merge(institution);
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		} catch (TransactionRequiredException e) {
			throw new VirtualShelterException(e);
		}
	}

	@Override
	@Transactional
	public void deleteById(long institutionId) throws VirtualShelterException {
		try {
			Query deleteQuery = em.createQuery(
					"DELETE FROM Institution where id=:p")
					.setParameter("p", institutionId);
			deleteQuery.executeUpdate();
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		} 
	}

	@Override
	public List<Institution> listInstituitionsOwnedByUser(long userId) throws VirtualShelterException {
		try {
			TypedQuery<Institution> query = em.createQuery("SELECT i FROM Institution i where owner_id=:p",
					Institution.class).setParameter("p", userId);
			return query.getResultList();
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		} 
	}

}
