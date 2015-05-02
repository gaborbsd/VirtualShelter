package hu.bme.aut.vshelter.api;

import hu.bme.aut.vshelter.dal.BreedFacade;
import hu.bme.aut.vshelter.dal.InstitutionFacade;
import hu.bme.aut.vshelter.dal.SpeciesFacade;
import hu.bme.aut.vshelter.dal.UserFacade;
import hu.bme.aut.vshelter.entity.Breed;
import hu.bme.aut.vshelter.entity.Institution;
import hu.bme.aut.vshelter.entity.Species;
import hu.bme.aut.vshelter.entity.User;

import java.util.List;

import javax.inject.Inject;

public class SiteAdministrationOperationsImpl implements
		ISiteAdministrationOperations {
	
	@Inject
	UserFacade userFacade;
	@Inject
	SpeciesFacade speciesFacade;
	@Inject
	BreedFacade breedFacade;
	@Inject
	private InstitutionFacade institutionFacade;

	@Override
	public List<User> listAllUsers() throws VirtualShelterException {
		return userFacade.findAll();
	}

	@Override
	public void promoteSiteAdministrator(long userId)
			throws VirtualShelterException {
		userFacade.promoteUserToSiteAdministrator(userId);
	}

	@Override
	public void revokeSiteAdministrator(long userId)
			throws VirtualShelterException {
		userFacade.revokeUserFromSiteAdministrator(userId);
	}

	@Override
	public void addSpecies(String speciesName) throws VirtualShelterException {
		Species species = new Species();
		species.setSpeciesName(speciesName);
		speciesFacade.create(species);

	}

	@Override
	public void deleteSpecies(long speciesId) throws VirtualShelterException {
		speciesFacade.deleteSpeciesById(speciesId);
	}

	@Override
	public void addBreed(String breedName, long speciesId) throws VirtualShelterException {
		Breed breed = new Breed();
		breed.setBreedName(breedName);
		Species species = speciesFacade.findSpeciesById(speciesId);
		breed.setSpecies(species);
		breedFacade.create(breed);
	}

	@Override
	public void deleteBreed(long breedId) throws VirtualShelterException {
		breedFacade.deleteBreedById(breedId);
	}

	@Override
	public List<User> listInstitutionAdministrators(long institutionId) {
		Institution institution = institutionFacade.findInstitutionById(institutionId);
		return institution.getInstitutionAdministrators();
	}

}
