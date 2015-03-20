package hu.bme.aut.vshelter.api;

import hu.bme.aut.vshelter.entity.*;

import java.util.List;

public interface IAdvertisementOperations {
	
	/**
	 * Advertises the given advertisement in the name of the given institution
	 * @param instituion the creator of the advertisement
	 * @param advertisement contains the advertisement's data
	 * @throws VirtualShelterException
	 */
	public void advertise(Institution instituion, Advertisement advertisement) throws VirtualShelterException;
	
	/**
	 * Advertises the given advertisement in the name of the logged in user
	 * @param advertisement contains the advertisement's data
	 * @throws VirtualShelterException
	 */
	public void advertise(Advertisement advertisement) throws VirtualShelterException;

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
	 * @param advertiser for this user (advertiser) will be the picture uploaded
	 * @throws VirtualShelterException
	 */
	public void uploadPicture(Picture picture, Advertiser advertiser) throws VirtualShelterException;
	
	/**
	 * Deletes a picture from a user account (from the given advertiser)
	 * @param picture
	 * @param advertiser
	 * @throws VirtualShelterException
	 */
	public void deletePicture(Picture picture, Advertiser advertiser) throws VirtualShelterException; 
	
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
	 * @param user the new owner
	 * @param institution the owner of this instituion's will be changed
	 * @throws VirtualShelterException
	 */
	public void changeInstitutionOwner(User user, Institution institution) throws VirtualShelterException;
	
	/**
	 * Adds to the given institution a new administrator
	 * @param user the new institution administrator
	 * @param institution this institution will get a new administrator
	 * @throws VirtualShelterException
	 */
	public void addInstitutionAdministrator(User user, Institution institution) throws VirtualShelterException;
	
	/**
	 * Deletes the administrator with the given userID from the institution with the given instituionID
	 * @param userId the deleted institution administrator's userID 
	 * @param institutionId from the institution with that id will be the administrator deleted 
	 * @throws VirtualShelterException
	 */
	public void deleteInstitutionAdministrator(long userId, long institutionId) throws VirtualShelterException;
	
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
}