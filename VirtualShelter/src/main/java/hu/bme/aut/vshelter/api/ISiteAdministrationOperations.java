package hu.bme.aut.vshelter.api;

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
	 * @param userId
	 * @return
	 */
	public User findUserById(long userId);
	
	/**
	 * Promotes a user to site administrator
	 * @param userId user to be promoted to site administrator
	 * @throws VirtualShelterException
	 */
	public void promoteSiteAdministrator(long userId) throws VirtualShelterException;
	
	/**
	 * Revokes a user from site administrator
	 * @param userId
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
	 * Returns the Institution(s) owned by the user with the given userId
	 * @param userId
	 * @return
	 */
	public List<Institution> listInstituitionsOwnedByUser(long userId);
	
}
