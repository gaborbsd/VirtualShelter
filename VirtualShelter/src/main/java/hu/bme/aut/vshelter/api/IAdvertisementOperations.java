package hu.bme.aut.vshelter.api;

import hu.bme.aut.vshelter.entity.*;

import java.util.List;
import java.util.Set;

public interface IAdvertisementOperations {
	
	/**
	 * Advertises the given advertisement in the name of the given institution
	 * @param instituionID the creator of the advertisement
	 * @param advertisementID contains the advertisement's data
	 * @throws VirtualShelterException
	 */
	public void advertise(long instituionID, long advertisementID) throws VirtualShelterException;
	
	/**
	 * Advertises the given advertisement in the name of the logged in user
	 * @param advertisementId contains the advertisement's data
	 * @throws VirtualShelterException
	 */
	public void advertise(long advertisementId) throws VirtualShelterException;

	/**
	 * Registers a user if there is no other user with the given e-mail address
	 * @param user contains the user's data to be registered 
	 * @throws VirtualShelterException
	 */
	public void registerUser(User user) throws VirtualShelterException;
	
	/**
	 * Deletes the user from the database with the given userId
	 * @param userId the user with that id will be deleted from the database
	 * @throws VirtualShelterException
	 */
	public void deleteUser(long userId) throws VirtualShelterException;
	
	/**
	 * Updates the user in the database with the given User's data 
	 * @param user user contains the user's data to be updated
	 * @throws VirtualShelterException
	 */
	public void updateUser(User user) throws VirtualShelterException;
	
	/**
	 * Uploads a picture to a user account (for the given advertiser)
	 * @param picture picture to be uploaded
	 * @param advertiserId for this user (advertiser) will be the picture uploaded
	 * @throws VirtualShelterException
	 */
	public void uploadPicture(Picture picture, long advertiserId) throws VirtualShelterException;
	
	/**
	 * Deletes a picture from a user account (from the given advertiser)
	 * @param pictureId
	 * @param advertiserId
	 * @throws VirtualShelterException
	 */
	public void deletePicture(long pictureId, long advertiserId) throws VirtualShelterException; 
	
	/**
	 * Creates an institution if there is no other institution with the given e-mail address
	 * @param institution contains the institution's data to be registered 
	 * @throws VirtualShelterException
	 */
	public void registerInstitution(Institution institution) throws VirtualShelterException;
	
	
	/**
	 * Deletes the institution from the database with the given instituionID
	 * @param institutionId the institution with that id will be deleted from the database
	 * @throws VirtualShelterException
	 */
	public void deleteInstitution(long institutionId) throws VirtualShelterException;
	
	/**
	 * Updates the institution in the database with the given Instituiton's data 
	 * @param institution contains the institution's data to be updated
	 * @throws VirtualShelterException
	 */
	public void updateInstitution(Institution institution) throws VirtualShelterException;
	
	/**
	 * Changes the given instituion's owner
	 * @param userId the new owner
	 * @param institutionId the owner of this instituion's will be changed
	 * @throws VirtualShelterException
	 */
	public void changeInstitutionOwner(long userId, long institutionId) throws VirtualShelterException;
	
	/**
	 * Adds to the given institution a new administrator
	 * @param userId the new institution administrator
	 * @param institutionId this institution will get a new administrator
	 * @throws VirtualShelterException
	 */
	public void addInstitutionAdministrator(long userId, long institutionId) throws VirtualShelterException;
	
	/**
	 * Deletes the administrator with the given userID from the institution with the given instituionID
	 * @param userId the deleted institution administrator's userID 
	 * @param institutionId from the institution with that id will be the administrator deleted 
	 * @throws VirtualShelterException
	 */
	public void deleteInstitutionAdministrator(long userId, long institutionId) throws VirtualShelterException;
	
	
	/**
	 * Returns the institution administrators for the institution with the given id
	 * @param institutionId
	 * @return
	 */
	public List<User> listInstitutionAdministrators(long institutionId);
	
	/**
	 * Creates an advertisement
	 * @param advertisement contains the advertisement's data to be created
	 * @throws VirtualShelterException
	 */	
	public void createAdvertisement(Advertisement advertisement) throws VirtualShelterException;
	
	/**
	 * Deletes an advertisement with the given advertisementId
	 * @param advertisementId the advertisement with this advertisementId will be deleted from the database
	 * @throws VirtualShelterException
	 */
	public void deleteAdvertisement(long advertisementId) throws VirtualShelterException;
	
	/**
	 * Returns a list of Advertisements which are satisfied the conditions
	 * @param aqf Contains the search's conditions
	 * @return
	 * @throws VirtualShelterException
	 */
	public List<Advertisement> searchAdvertisements(AdvertisementQueryFilter aqf) throws VirtualShelterException;
	
	/**
	 * Returns with all of the advertisements
	 * @return
	 * @throws VirtualShelterException
	 */
	public List<Advertisement> listAllAdvertisements() throws VirtualShelterException;
	
	/**
	 * Returns all of the species
	 * @return
	 * @throws VirtualShelterException
	 */
	public List<Species> listAllSpecies() throws VirtualShelterException;
	
	/**
	 * Returns all of the breeds
	 * @return
	 * @throws VirtualShelterException
	 */
	public List<Breed> listAllBreeds() throws VirtualShelterException;
	
	public void addAnimal(Animal animal) throws VirtualShelterException;
	
	public void deleteAnimal(long animalId) throws VirtualShelterException;
	
	/**
	 * Returns the advertisements of the advertiser(user or institution)
	 * @param advertiserId
	 * @return
	 */
	public List<Advertisement> listAdvertisementsFromAdvertiser(long advertiserId);
	
	/**
	 * Returns the species by the given speciesId
	 * @param speciesId
	 * @return
	 */
	public Species findSpeciesById(long speciesId);
	
	/**
	 * Updates the species identified by given species' id
	 * @param species
	 */
	public void updateSpecies(Species species);
	
	/**
	 * list all breeds of the given speciesId
	 * @param speciesId
	 */
	public Set<Breed> listBreedsOfTheSpecies(long speciesId);
	
	/**
	 * Returns the animal identified by the given animalId
	 * @param animalID
	 * @return
	 */
	public Animal findAnimalById(long animalId);
	
	/**
	 * Updates the animal identified by the given animal's id
	 * @param animal
	 */
	public void updateAnimal(Animal animal);
	
	/**
	 * Returns the Advertiser of the Animal with the given animalId
	 * @param animalId
	 * @return
	 */
	public Advertiser getAdvertiserOfAnimal(long animalId);
	
	/**
	 * Lists the animals advertised by the advertiser with the given advertiserId: 
	 * @param advertiserId
	 * @return
	 */
	public List<Animal> listAnimalsAdvertisedByAdvertiser(long advertiserId);
}
