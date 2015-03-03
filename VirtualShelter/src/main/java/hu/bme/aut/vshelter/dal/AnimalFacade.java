package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Animal;

import java.util.List;

public interface AnimalFacade {
	void create(Animal animal);

	List<Animal> findAll();
}
