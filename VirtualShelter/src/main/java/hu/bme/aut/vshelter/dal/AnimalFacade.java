package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Animal;

import java.util.List;

public interface AnimalFacade {
	
	Animal findAnimalById(int animalId);
	
	List<Animal> findAll();
	
	void create(Animal animal);
	
	void edit(Animal animal);
	
	void deleteAnimalById(int animalId);
	
}
