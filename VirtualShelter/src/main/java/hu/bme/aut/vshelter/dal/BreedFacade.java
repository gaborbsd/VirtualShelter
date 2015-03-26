package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Breed;

import java.util.List;

public interface BreedFacade {
		
	Breed findBreedById(int breedId);
	
	List<Breed> findAll();
	
	void create(Breed breed);
	
	void edit(Breed breed);
	
	void deleteBreedById(int breedId);


}
