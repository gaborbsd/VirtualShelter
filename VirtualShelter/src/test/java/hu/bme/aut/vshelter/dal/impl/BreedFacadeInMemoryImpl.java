package hu.bme.aut.vshelter.dal.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import hu.bme.aut.vshelter.entity.Breed;
import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.dal.BreedFacade;

public class BreedFacadeInMemoryImpl implements BreedFacade {

	private List<Breed> breeds = new ArrayList<Breed>();

	@Override
	public Breed findById(long breedId) {
		try {
			return breeds.stream()
					.filter(a -> a.getId() == breedId).findFirst()
					.get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public List<Breed> findAll() {
		return Collections.unmodifiableList(breeds);
	}

	@Override
	public void create(Breed breed) {
		breeds.add(breed);
	}

	@Override
	public void edit(Breed breed) {
		breeds
			.set(breeds.indexOf(breed), breed);
	}

	@Override
	public void deleteById(long breedId) {
		Breed deleteBreed = findById(breedId);

		if (deleteBreed != null)
			breeds.remove(deleteBreed);

	}
	
}
