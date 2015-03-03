package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.dal.AnimalFacade;
import hu.bme.aut.vshelter.entity.Animal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
}
