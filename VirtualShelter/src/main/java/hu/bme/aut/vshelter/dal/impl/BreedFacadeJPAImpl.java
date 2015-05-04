package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.dal.BreedFacade;
import hu.bme.aut.vshelter.entity.Breed;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import javax.persistence.TypedQuery;

public class BreedFacadeJPAImpl implements BreedFacade {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public Breed findById(long breedId) throws VirtualShelterException {
		try {
			return em.find(Breed.class, breedId);
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		}
	}

	@Override
	@Transactional
	public List<Breed> findAll() throws VirtualShelterException {
		try {
			TypedQuery<Breed> query = em.createQuery("SELECT b FROM Breed b",
					Breed.class);
	
			return query.getResultList();
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		}
	}

	@Override
	@Transactional
	public void create(Breed breed) throws VirtualShelterException {
		try {
			em.persist(breed);
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
	public void edit(Breed breed) throws VirtualShelterException {
		try {
			em.merge(breed);
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
	public void deleteById(long breedId) throws VirtualShelterException {
		try {
			Query deleteQuery = em.createQuery(
					"DELETE FROM Breed where id=:p")
					.setParameter("p", breedId);
			deleteQuery.executeUpdate();
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		}
	}

}
