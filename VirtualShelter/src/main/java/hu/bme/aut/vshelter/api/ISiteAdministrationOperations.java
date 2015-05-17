package hu.bme.aut.vshelter.api;

import hu.bme.aut.vshelter.entity.Breed;
import hu.bme.aut.vshelter.entity.Institution;
import hu.bme.aut.vshelter.entity.User;

import java.util.List;

public interface ISiteAdministrationOperations {
	
	/**
	 * Returns with all users of the virtual shelter.
	 * @return
	 * @throws VirtualShelterException
	 */
	public List<User> listAllUsers() throws VirtualShelterException;
	
	/**
	 * Returns user with the give id
	 * @param userId the user with that userId will be returned
	 * @return
	 */
	public User findUserById(long userId) throws VirtualShelterException;
	
	/**
	 * Returns whether the user is a site administrator or not
	 * @return
	 */
	public boolean isUserSiteAdministrator(long userId) throws VirtualShelterException;
	
	/**
	 * Promotes a user to site administrator
	 * @param userId user to be promoted to site administrator
	 * @throws VirtualShelterException
	 */
	public void promoteSiteAdministrator(long userId) throws VirtualShelterException;
	
	/**
	 * Revokes a user from site administrator
	 * @param userId user to be revoked from site administrator
	 * @throws VirtualShelterException
	 */
	public void revokeSiteAdministrator(long userId) throws VirtualShelterException;
	
	/**
	 * Adds a new species to the database
	 * @param speciesName name of the species to be added
	 * @throws VirtualShelterException
	 */
	public void addSpecies(String speciesName) throws VirtualShelterException;
	
	/**
	 * Deletes a species from database
	 * @param speciesId name of the species to be deleted
	 * @throws VirtualShelterException
	 */
	public void deleteSpecies(long speciesId) throws VirtualShelterException;
	
	/**
	 * Adds a new breed to the database
	 * @param breedName name of the breed to be added
	 * @param speciesId species name of the breed
	 * @throws VirtualShelterException
	 */
	public void addBreed(String breedName, long speciesId) throws VirtualShelterException;
	
	/**
	 * Deletes a breed from the database
	 * @param breedId name of the breed to be deleted
	 * @throws VirtualShelterException
	 */
	public void deleteBreed(long breedId) throws VirtualShelterException;
	
	/**
	 * Returns the owned Institution(s) by the user with the given userId
	 * @param userId identifies the user
	 * @return
	 */
	public List<Institution> listInstituitionsOwnedByUser(long userId) throws VirtualShelterException;
	
	/**
	 * Updates the breed identified by the given breed's id
	 * @param breed identifies the breed
	 */
	public void updateBreed(Breed breed) throws VirtualShelterException;
	
	/**
	 * Returns the owner of the given institution's id
	 * @param institutionId the owner for the institution identified with that id will be returned 
	 * @return
	 */
	public User findOwnerOfInstitution(long institutionId) throws VirtualShelterException;
	
}
