package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Species;

import java.util.List;

public interface SpeciesFacade {
	
	Species findSpeciesById(int speciesId);
	
	List<Species> findAll();
	
	void create(Species Species);
	
	void edit(Species Species);
	
	void deleteSpeciesById(int speciesId);

}
