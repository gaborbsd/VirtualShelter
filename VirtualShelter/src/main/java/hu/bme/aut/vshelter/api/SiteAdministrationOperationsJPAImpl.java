package hu.bme.aut.vshelter.api;

import hu.bme.aut.vshelter.dal.BreedFacade;
import hu.bme.aut.vshelter.dal.impl.AddressFacadeJPAImpl;
import hu.bme.aut.vshelter.dal.impl.BreedFacadeJPAImpl;
import hu.bme.aut.vshelter.dal.impl.SpeciesFacadeJPAImpl;
import hu.bme.aut.vshelter.dal.impl.UserFacadeJPAImpl;
import hu.bme.aut.vshelter.entity.Breed;
import hu.bme.aut.vshelter.entity.Species;
import hu.bme.aut.vshelter.entity.User;

import java.util.List;

public class SiteAdministrationOperationsJPAImpl implements
		ISiteAdministrationOperations {
	
	UserFacadeJPAImpl userDaO = new UserFacadeJPAImpl();
	SpeciesFacadeJPAImpl speciesDAO = new SpeciesFacadeJPAImpl();
	BreedFacadeJPAImpl breedDAO = new BreedFacadeJPAImpl();

	@Override
	public List<User> listAllUsers() throws VirtualShelterException {
		return userDaO.findAll();
	}

	@Override
	public void promoteSiteAdministrator(User user)
			throws VirtualShelterException {
		userDaO.promoteUserToSiteAdministrator(user.getId());
	}

	@Override
	public void revokeSiteAdministrator(User user)
			throws VirtualShelterException {
		userDaO.revokeUserFromSiteAdministrator(user.getId());
	}

	@Override
	public void addSpecies(String speciesName) throws VirtualShelterException {
		Species species = new Species();
		species.setSpeciesName(speciesName);
		speciesDAO.create(species);

	}

	@Override
	public void deleteSpecies(String speciesName) throws VirtualShelterException {
		speciesDAO.deleteSpeciesById(speciesDAO.getSpeciesIdfromSpeciesName(speciesName));
	}

	@Override
	public void addBreed(String breedName, String speciesName) throws VirtualShelterException {
		Breed breed = new Breed();
		breed.setBreedName(breedName);
		Species species = speciesDAO.findSpeciesById(speciesDAO.getSpeciesIdfromSpeciesName(speciesName));
		breed.setSpecies(species);
		breedDAO.create(breed);
	}

	@Override
	public void deleteBreed(String breedName) throws VirtualShelterException {
		breedDAO.deleteBreedById(breedDAO.getBreedIdfromSpeciesName(breedName));
	}

}
