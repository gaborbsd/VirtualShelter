package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.dal.SpeciesFacade;
import hu.bme.aut.vshelter.entity.Species;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class SpeciesFacadeInMemoryImpl implements SpeciesFacade {

	private List<Species> specieses = new ArrayList<Species>();

	@Override
	public Species findSpeciesById(long speciesId) {
		try {
			return specieses.stream().filter(a -> a.getId() == speciesId)
					.findFirst().get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public List<Species> findAll() {
		return Collections.unmodifiableList(specieses);
	}

	@Override
	public void create(Species species) {
		specieses.add(species);
	}

	@Override
	public void edit(Species species) {
		specieses.set(specieses.indexOf(species), species);
	}

	@Override
	public void deleteSpeciesById(long speciesId) {
		Species deleteSpecies = findSpeciesById(speciesId);

		if (deleteSpecies != null)
			specieses.remove(deleteSpecies);
	}

	@Override
	public long getSpeciesIdfromSpeciesName(String speciesName)
			throws VirtualShelterException {
		try {
			return specieses.stream()
					.filter(a -> a.getSpeciesName() == speciesName).findFirst()
					.get().getId();
		} catch (NoSuchElementException e) {
			return 0;
		}
	}

}
