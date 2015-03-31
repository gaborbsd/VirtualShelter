package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.dal.AnimalFacade;
import hu.bme.aut.vshelter.entity.Advertisement;
import hu.bme.aut.vshelter.entity.Animal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

public class AnimalFacadeJPAImpl implements AnimalFacade {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void create(Animal animal) {
		em.persist(animal);
	}

	@Override
	@Transactional
	public List<Animal> findAll() {
		TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a",
				Animal.class);

		return query.getResultList();
	}

	@Override
	@Transactional
	public Animal findAnimalById(int animalId) {
		return em.find(Animal.class, animalId);
	}

	@Override
	@Transactional
	public void edit(Animal animal) {
		em.merge(animal);
	}

	@Override
	@Transactional
	public void deleteAnimalById(int animalId) {
		Query deleteQuery = em.createQuery(
				"DELETE FROM Advertisement where id=:p")
				.setParameter("p", animalId);
		deleteQuery.executeUpdate();
	}
}