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

public class AdvertisementOperationsImpl implements IAdvertisementOperations {
	
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
	
	@Override
	public void advertise(long instituionId, long animalId)
			throws VirtualShelterException {
		Advertisement advertisement = new Advertisement();
		Institution institution = institutionFacade.findById(instituionId);
		advertisement.setAdvertiser(institution);
		Animal animal = animalFacade.findById(animalId);
		advertisement.setAnimal(animal);
		advertisement.setDateOfAdvertisement(Calendar.getInstance());
		advertismentFacade.create(advertisement);
		
	}

	@Override
	public void advertise(long animalId)
			throws VirtualShelterException {
		Advertisement advertisement = new Advertisement();
		User user = userFacade.findById(1);
		advertisement.setAdvertiser(user);
		Animal animal = animalFacade.findById(animalId);
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
		userFacade.deleteById(userId);
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
		institutionFacade.deleteById(institutionId);

	}

	@Override
	public void updateInstitution(Institution institution)
			throws VirtualShelterException {
		institutionFacade.edit(institution);

	}

	@Override
	public void changeInstitutionOwner(long userId, long institutionId)
			throws VirtualShelterException {
		User owner = userFacade.findById(userId);
		Institution institution = institutionFacade.findById(institutionId);
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
	public List<User> listInstitutionAdministrators(long institutionId) throws VirtualShelterException {
		Institution institution = institutionFacade.findById(institutionId);
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
		advertismentFacade.deleteById(advertisementId);
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
		animalFacade.deleteById(animalId);
	}

	@Override
	public List<Advertisement> listAdvertisementsFromAdvertiser(
			long advertiserId) throws VirtualShelterException {
		return advertismentFacade.listAdvertisementsFromAdvertiser(advertiserId);
	}

	@Override
	public Species findSpeciesById(long speciesId) throws VirtualShelterException {
		return speciesFacade.findById(speciesId);
	}

	@Override
	public void updateSpecies(Species species) throws VirtualShelterException {
		speciesFacade.edit(species);
	}

	@Override
	public Set<Breed> listBreedsOfTheSpecies(long speciesId) throws VirtualShelterException {
		Species species = speciesFacade.findById(speciesId);
		return species.getBreeds();
	}

	@Override
	public Animal findAnimalById(long animalId) throws VirtualShelterException {
		return animalFacade.findById(animalId);
	}

	@Override
	public void updateAnimal(Animal animal) throws VirtualShelterException {
		animalFacade.edit(animal);
	}

	@Override
	public Advertiser getAdvertiserOfAnimal(long animalId) throws VirtualShelterException {
		return advertismentFacade.getAdvertiserOfAnimal(animalId);
	}

	@Override
	public List<Animal> listAnimalsAdvertisedByAdvertiser(long advertiserId) throws VirtualShelterException {
		return advertismentFacade.listAnimalsAdvertisedByAdvertiser(advertiserId);
	}

	@Override
	public List<Institution> listInstitutions() throws VirtualShelterException {
		return institutionFacade.findAll();
	}

	@Override
	public Institution getInstitutionById(long institutionId)
			throws VirtualShelterException {
		return institutionFacade.findById(institutionId);
	}

	@Override
	public List<Picture> listInstitutionPictures(long institutionId)
			throws VirtualShelterException {
		Institution institution = institutionFacade.findById(institutionId);		
		return institution.getPicturesList();
	}

	@Override
	public Picture getInstitutionProfilePicture(long institutionId)
			throws VirtualShelterException {
		Institution institution = institutionFacade.findById(institutionId);
		return institution.getProfilePicture();
	}

	@Override
	public void postNewProfilePictureForInstitution(long institutionId, Picture profilePicture)
			throws VirtualShelterException {
		Institution institution = institutionFacade.findById(institutionId);
		institution.setProfilePicture(profilePicture);
	}

	@Override
	public void deleteProfilePictureForInstitution(long institutionId)
			throws VirtualShelterException {
		Institution institution = institutionFacade.findById(institutionId);
		institution.setProfilePicture(null);
	}

	@Override
	public void postNewPictureForInstitution(long institutionId,
			Picture profilePicture) throws VirtualShelterException {
		Institution institution = institutionFacade.findById(institutionId);
		List<Picture> pictureList = institution.getPicturesList();
		pictureList.add(profilePicture);
		institution.setPicturesList(pictureList);	
	}

	@Override
	public void deletePictureForInstitution(long institutionId,
			Picture picture) throws VirtualShelterException {
		Institution institution = institutionFacade.findById(institutionId);
		List<Picture> pictureList = institution.getPicturesList();
		pictureList.remove(picture);
		institution.setPicturesList(pictureList);	
		
	}

	@Override
	public List<Picture> listUserPictures(long userId)
			throws VirtualShelterException {
		User user = userFacade.findById(userId);
		return user.getPicturesList();
	}

	@Override
	public Picture getUserProfilePicture(long userId)
			throws VirtualShelterException {
		User user = userFacade.findById(userId);
		return user.getProfilePicture();
	}

	@Override
	public void postNewProfilePictureForUser(long userId, Picture profilePicture)
			throws VirtualShelterException {
		User user = userFacade.findById(userId);
		user.setProfilePicture(profilePicture);	
	}

	@Override
	public void deleteProfilePictureForUser(long userId)
			throws VirtualShelterException {
		User user = userFacade.findById(userId);
		user.setProfilePicture(null);	
		
	}

	@Override
	public void postNewPictureForUser(long userId, Picture picture)
			throws VirtualShelterException {
		User user = userFacade.findById(userId);
		List<Picture> pictures = user.getPicturesList();
		pictures.add(picture);
		user.setPicturesList(pictures);	
	}

	@Override
	public void deletePictureForUser(long userId, Picture picture)
			throws VirtualShelterException {
		User user = userFacade.findById(userId);
		List<Picture> pictures = user.getPicturesList();
		pictures.remove(picture);
		user.setPicturesList(pictures);
	}

	@Override
	public List<Picture> listAnimalPictures(long animalId)
			throws VirtualShelterException {
		Animal animal = animalFacade.findById(animalId);
		return animal.getPicturesList();
	}

	@Override
	public void postNewPictureForAnimal(long animalId, Picture picture)
			throws VirtualShelterException {
		Animal animal = animalFacade.findById(animalId);
		List<Picture> pictures = animal.getPicturesList();
		pictures.add(picture);
		animal.setPictureList(pictures);		
	}

	@Override
	public Picture getAnimalProfilePicture(long animalId)
			throws VirtualShelterException {
		Animal animal = animalFacade.findById(animalId);
		return animal.getProfilePicture();
	}

	@Override
	public void postNewProfilePictureForAnimal(long animalId,
			Picture profilePicture) throws VirtualShelterException {
		Animal animal = animalFacade.findById(animalId);
		animal.setProfilePicture(profilePicture);	
	}

	@Override
	public void deleteProfilePictureForAnimal(long animalId)
			throws VirtualShelterException {
		Animal animal = animalFacade.findById(animalId);
		animal.setProfilePicture(null);	
		
	}

	@Override
	public void deletePictureForAnimal(long animalId, Picture picture)
			throws VirtualShelterException {
		Animal animal = animalFacade.findById(animalId);
		List<Picture> pictures = animal.getPicturesList();
		pictures.remove(picture);
		animal.setPictureList(pictures);
	}

}
