package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Animal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
	public Animal findAnimalById(int animalId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void edit(Animal animal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAnimalById(int animalId) {
		// TODO Auto-generated method stub
		
	}
}
