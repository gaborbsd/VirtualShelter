package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.dal.BreedFacade;
import hu.bme.aut.vshelter.entity.Breed;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.persistence.TypedQuery;

public class BreedFacadeJPAImpl implements BreedFacade {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public Breed findBreedById(long breedId) {
		return em.find(Breed.class, breedId);
	}

	@Override
	@Transactional
	public List<Breed> findAll() {
		TypedQuery<Breed> query = em.createQuery("SELECT b FROM Breed b",
				Breed.class);

		return query.getResultList();
	}

	@Override
	@Transactional
	public void create(Breed breed) {
		em.persist(breed);
	}

	@Override
	@Transactional
	public void edit(Breed breed) {
		em.merge(breed);
	}

	@Override
	@Transactional
	public void deleteBreedById(long breedId) {
		Query deleteQuery = em.createQuery(
				"DELETE FROM Breed where id=:p")
				.setParameter("p", breedId);
		deleteQuery.executeUpdate();
	}

	@Override
	public int getBreedIdfromSpeciesName(String breedName)
			throws VirtualShelterException {
		List<Integer> list = em.createQuery(
		        "SELECT b.id FROM Breed b WHERE b.breedName = ?1").setParameter(1, breedName).getResultList();
		return list.get(0);
	}
	

}
