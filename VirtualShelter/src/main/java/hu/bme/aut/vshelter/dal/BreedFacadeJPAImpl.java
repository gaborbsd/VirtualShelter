package hu.bme.aut.vshelter.dal;

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
	public Breed findBreedById(int breedId) {
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
	public void deleteBreedById(int breedId) {
		Query deleteQuery = em.createQuery(
				"DELETE FROM Breed where id=:p")
				.setParameter("p", breedId);
		deleteQuery.executeUpdate();
	}

}
