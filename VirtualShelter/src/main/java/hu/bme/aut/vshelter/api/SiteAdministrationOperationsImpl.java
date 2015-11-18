package hu.bme.aut.vshelter.api;

import hu.bme.aut.vshelter.dal.BreedFacade;
import hu.bme.aut.vshelter.dal.BreedRepository;
import hu.bme.aut.vshelter.dal.InstitutionFacade;
import hu.bme.aut.vshelter.dal.InstitutionRepository;
import hu.bme.aut.vshelter.dal.SpeciesFacade;
import hu.bme.aut.vshelter.dal.SpeciesRepository;
import hu.bme.aut.vshelter.dal.UserFacade;
import hu.bme.aut.vshelter.dal.UserRepository;
import hu.bme.aut.vshelter.entity.Breed;
import hu.bme.aut.vshelter.entity.Institution;
import hu.bme.aut.vshelter.entity.Species;
import hu.bme.aut.vshelter.entity.User;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;

public class SiteAdministrationOperationsImpl implements
		ISiteAdministrationOperations {

// Before Spring Data we used these Facades
//	@Inject
//	UserFacade userFacade;
//	@Inject
//	SpeciesFacade speciesFacade;
//	@Inject
//	BreedFacade breedFacade;
//	@Inject
//	private InstitutionFacade institutionFacade;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SpeciesRepository speciesRepository;
	@Autowired
	private BreedRepository breedRepository;
	@Autowired
	private InstitutionRepository institutionRepository;

	@Override
	public List<User> listAllUsers() throws VirtualShelterException {
		return userRepository.findAll();
	}

	@Override
	public User findUserById(long userId) throws VirtualShelterException {
		return userRepository.findById(userId);
	}
	
	@Override
	public boolean isUserSiteAdministrator(long userId) throws VirtualShelterException {
		User user = userRepository.findById(userId);
		
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
		userRepository.promoteUserToSiteAdministrator(userId);
	}

	@Override
	public void revokeSiteAdministrator(long userId)
			throws VirtualShelterException {
		userRepository.revokeUserFromSiteAdministrator(userId);
	}

	@Override
	public void addSpecies(String speciesName) throws VirtualShelterException {
		Species species = new Species();
		species.setSpeciesName(speciesName);
		speciesRepository.save(species);

	}

	@Override
	public void deleteSpecies(long speciesId) throws VirtualShelterException {
		speciesRepository.delete(Long.valueOf(speciesId));
	}

	@Override
	public void addBreed(String breedName, long speciesId) throws VirtualShelterException {
		Breed breed = new Breed();
		breed.setBreedName(breedName);
		Species species = speciesRepository.findOne(Long.valueOf(speciesId));
		breedRepository.save(breed);
		species.getBreeds().add(breed);
		speciesRepository.edit(species);
	}

	@Override
	public void deleteBreed(long breedId) throws VirtualShelterException {
		breedRepository.delete(Long.valueOf(breedId));
	}

	@Override
	public List<Institution> listInstituitionsOwnedByUser(long userId) throws VirtualShelterException {
		return institutionRepository.listInstituitionsOwnedByUser(userId);
	}

	@Override
	public void updateBreed(Breed breed) throws VirtualShelterException {
		breedRepository.edit(breed);
	}

	@Override
	public User findOwnerOfInstitution(long institutionId) throws VirtualShelterException {
		Institution institution = institutionRepository.findOne(Long.valueOf(institutionId));
		return institution.getOwner();
	}

}
