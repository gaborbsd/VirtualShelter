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
	public User findUserById(long userId) {
		return userFacade.findUserById(userId);
	}
	
	@Override
	public boolean isUserSiteAdministrator(long userId) {
		User user = userFacade.findUserById(userId);
		
		String role = "site-administrator";

		if (!user.getRoles().contains(role)) {
			return true;
		} else {
			return false;
		}
		
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
	public List<Institution> listInstituitionsOwnedByUser(long userId) {
		return institutionFacade.listInstituitionsOwnedByUser(userId);
	}

	@Override
	public void updateBreed(Breed breed) {
		breedFacade.edit(breed);
	}

	@Override
	public User findOwnerOfInstitution(long institutionId) {
		Institution institution = institutionFacade.findInstitutionById(institutionId);
		return institution.getOwner();
	}

}
