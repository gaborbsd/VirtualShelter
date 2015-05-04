package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.dal.SpeciesFacade;
import hu.bme.aut.vshelter.entity.Species;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import javax.persistence.TypedQuery;

public class SpeciesFacadeJPAImpl implements SpeciesFacade {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public Species findById(long speciesId) throws VirtualShelterException {
		try {
			return em.find(Species.class, speciesId);
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		}
	}

	@Override
	@Transactional
	public List<Species> findAll() throws VirtualShelterException {
		try {
			TypedQuery<Species> query = em.createQuery("SELECT s FROM Species s",
					Species.class);
	
			return query.getResultList();
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		}
	}

	@Override
	@Transactional
	public void create(Species species) throws VirtualShelterException {
		try {
			em.persist(species);
		} catch (EntityExistsException e) {
			throw new VirtualShelterException(e);
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		} catch (TransactionRequiredException e) {
			throw new VirtualShelterException(e);
		} catch (ValidationException e) {
			throw new VirtualShelterException(e);
		} 
	}

	@Override
	@Transactional
	public void edit(Species species) throws VirtualShelterException {
		try {
			em.merge(species);
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		} catch (TransactionRequiredException e) {
			throw new VirtualShelterException(e);
		} catch (ValidationException e) {
			throw new VirtualShelterException(e);
		} 
	}

	@Override
	@Transactional
	public void deleteById(long speciesId) throws VirtualShelterException {
		try {
			Query deleteQuery = em.createQuery("DELETE FROM Species where id=:p")
					.setParameter("p", speciesId);
			deleteQuery.executeUpdate();
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		}
	}

	@Override
	public long getSpeciesIdfromSpeciesName(String speciesName)
			throws VirtualShelterException {
		try {
			TypedQuery<Long> query = em.createQuery(
					"SELECT s.id FROM Species s WHERE s.speciesName = ?1",
					Long.class).setParameter(1, speciesName);
			return query.getSingleResult();
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		}
	}

}
