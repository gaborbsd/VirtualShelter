package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Breed;

import java.util.List;

public interface BreedFacade {
		
	Breed findBreedById(int breedId);
	
	List<Breed> findAll();
	
	void create(Breed Breed);
	
	void edit(Breed Breed);
	
	void deleteBreedById(int breedId);


}
