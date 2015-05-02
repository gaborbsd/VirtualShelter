package hu.bme.aut.vshelter.api;

import hu.bme.aut.vshelter.dal.AdvertisementFacade;
import hu.bme.aut.vshelter.dal.AnimalFacade;
import hu.bme.aut.vshelter.dal.BreedFacade;
import hu.bme.aut.vshelter.dal.InstitutionFacade;
import hu.bme.aut.vshelter.dal.PictureFacade;
import hu.bme.aut.vshelter.dal.SpeciesFacade;
import hu.bme.aut.vshelter.dal.UserFacade;
import hu.bme.aut.vshelter.entity.Advertisement;
import hu.bme.aut.vshelter.entity.AdvertisementQueryFilter;
import hu.bme.aut.vshelter.entity.Advertiser;
import hu.bme.aut.vshelter.entity.Animal;
import hu.bme.aut.vshelter.entity.Breed;
import hu.bme.aut.vshelter.entity.Institution;
import hu.bme.aut.vshelter.entity.Picture;
import hu.bme.aut.vshelter.entity.Species;
import hu.bme.aut.vshelter.entity.User;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;

public class AdvertisementOperationsImpl implements IAdvertisementOperations {
	
	@Inject
	private AdvertisementFacade addressFacade;
	@Inject
	private UserFacade userFacade;
	@Inject
	private InstitutionFacade institutionFacade;
	@Inject
	private SpeciesFacade speciesFacade;
	@Inject
	private AdvertisementFacade advertismentFacade;
	@Inject
	private BreedFacade breedFacade;
	@Inject
	private AnimalFacade animalFacade;
	@Inject
	private PictureFacade pictureFacade;
	
	@Override
	public void advertise(long instituionId, long animalId)
			throws VirtualShelterException {
		Advertisement advertisement = new Advertisement();
		Institution institution = institutionFacade.findInstitutionById(instituionId);
		advertisement.setAdvertiser(institution);
		Animal animal = animalFacade.findAnimalById(animalId);
		advertisement.setAnimal(animal);
		advertisement.setDateOfAdvertisement(Calendar.getInstance());
		advertismentFacade.create(advertisement);
		
	}

	@Override
	public void advertise(long animalId)
			throws VirtualShelterException {
		Advertisement advertisement = new Advertisement();
		User user = userFacade.findUserById(1);
		advertisement.setAdvertiser(user);
		Animal animal = animalFacade.findAnimalById(animalId);
		advertisement.setAnimal(animal);
		advertisement.setDateOfAdvertisement(Calendar.getInstance());
		advertismentFacade.create(advertisement);

	}

	@Override
	public void registerUser(User user) throws VirtualShelterException {
		userFacade.create(user);
	}

	@Override
	public void deleteUser(long userId) throws VirtualShelterException {
		userFacade.deleteUserById(userId);
	}

	@Override
	public void updateUser(User user) throws VirtualShelterException {
		userFacade.edit(user);
	}

	@Override
	public void uploadPicture(Picture picture, long advertiserId)
			throws VirtualShelterException {
		// user vagy institution ??

	}

	@Override
	public void deletePicture(long pictureId, long advertiserId)
			throws VirtualShelterException {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerInstitution(Institution institution)
			throws VirtualShelterException {
		institutionFacade.create(institution);

	}

	@Override
	public void deleteInstitution(long institutionId)
			throws VirtualShelterException {
		institutionFacade.deleteInstitutionById(institutionId);

	}

	@Override
	public void updateInstitution(Institution institution)
			throws VirtualShelterException {
		institutionFacade.edit(institution);

	}

	@Override
	public void changeInstitutionOwner(long userId, long institutionId)
			throws VirtualShelterException {
		User owner = userFacade.findUserById(userId);
		Institution institution = institutionFacade.findInstitutionById(institutionId);
		institution.setOwner(owner);
		institutionFacade.edit(institution);

	}

	@Override
	public void addInstitutionAdministrator(long userId, long institutionId)
			throws VirtualShelterException {
		

	}

	@Override
	public void deleteInstitutionAdministrator(long userId, long institutionId)
			throws VirtualShelterException {
		
	}
	
	@Override
	public List<User> listInstitutionAdministrators(long institutionId) {
		Institution institution = institutionFacade.findInstitutionById(institutionId);
		return institution.getInstitutionAdministrators();
	}

	@Override
	public void createAdvertisement(Advertisement advertisement)
			throws VirtualShelterException {
		advertismentFacade.create(advertisement);

	}

	@Override
	public void deleteAdvertisement(long advertisementId)
			throws VirtualShelterException {
		advertismentFacade.deleteAdvertisementById(advertisementId);
	}

	@Override
	public List<Advertisement> searchAdvertisements(AdvertisementQueryFilter aqf)
			throws VirtualShelterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Advertisement> listAllAdvertisements()
			throws VirtualShelterException {
		return advertismentFacade.findAll();
	}

	@Override
	public List<Species> listAllSpecies() throws VirtualShelterException {
		return speciesFacade.findAll();
	}

	@Override
	public List<Breed> listAllBreeds() throws VirtualShelterException {
		return breedFacade.findAll();
	}

	@Override
	public void addAnimal(Animal animal) throws VirtualShelterException {
		animalFacade.create(animal);	
	}

	@Override
	public void deleteAnimal(long animalId) throws VirtualShelterException {
		animalFacade.deleteAnimalById(animalId);
	}

	@Override
	public List<Advertisement> listAdvertisementsFromAdvertiser(
			long advertiserId) {
		return advertismentFacade.listAdvertisementsFromAdvertiser(advertiserId);
	}

	@Override
	public Species findSpeciesById(long speciesId) {
		return speciesFacade.findSpeciesById(speciesId);
	}

	@Override
	public void updateSpecies(Species species) {
		speciesFacade.edit(species);
	}

	@Override
	public Set<Breed> listBreedsOfTheSpecies(long speciesId) {
		Species species = speciesFacade.findSpeciesById(speciesId);
		return species.getBreeds();
	}

	@Override
	public Animal findAnimalById(long animalId) {
		return animalFacade.findAnimalById(animalId);
	}

	@Override
	public void updateAnimal(Animal animal) {
		animalFacade.edit(animal);
	}

	@Override
	public Advertiser getAdvertiserOfAnimal(long animalId) {
		return advertismentFacade.getAdvertiserOfAnimal(animalId);
	}

}
