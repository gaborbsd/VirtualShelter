package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.entity.Species;

import java.util.List;

public interface SpeciesFacade {
	
	Species findSpeciesById(long speciesId);
	
	List<Species> findAll();
	
	void create(Species species);
	
	void edit(Species species);
	
	void deleteSpeciesById(long speciesId);
	
	/**
	 * Returns the speciesID from the owner of the given species name
	 * @return
	 * @throws VirtualShelterException
	 */
	public long getSpeciesIdfromSpeciesName(String speciesName)  throws VirtualShelterException;

}
