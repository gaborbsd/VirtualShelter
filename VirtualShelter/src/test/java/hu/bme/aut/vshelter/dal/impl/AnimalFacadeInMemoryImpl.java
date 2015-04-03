package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.dal.AnimalFacade;
import hu.bme.aut.vshelter.entity.Advertisement;
import hu.bme.aut.vshelter.entity.Animal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class AnimalFacadeInMemoryImpl implements AnimalFacade {

	private static List<Animal> animals = new ArrayList<Animal>();

	@Override
	public void create(Animal animal) {
		animals.add(animal);
	}

	@Override
	public List<Animal> findAll() {
		return Collections.unmodifiableList(animals);
	}

	@Override
	public Animal findAnimalById(long animalId) {
		try {
			return animals.stream()
					.filter(a -> a.getId() == animalId).findFirst()
					.get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public void edit(Animal animal) {
		animals
			.set(animals.indexOf(animal), animal);
		
	}

	@Override
	public void deleteAnimalById(long animalId) {
		Animal deleteAnimal = findAnimalById(animalId);

		if (deleteAnimal != null)
			animals.remove(deleteAnimal);
	
	}
}
