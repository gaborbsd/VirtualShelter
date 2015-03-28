package hu.bme.aut.vshelter.api;

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
	 * Promotes a user to site administrator
	 * @param user user to be promoted to site administrator
	 * @throws VirtualShelterException
	 */
	public void promoteSiteAdministrator(User user) throws VirtualShelterException;
	
	/**
	 * Revokes a user from site administrator
	 * @param user
	 * @throws VirtualShelterException
	 */
	public void revokeSiteAdministrator(User user) throws VirtualShelterException;
	
	/**
	 * Adds a new species to the database
	 * @param speciesName name of the species to be added
	 * @throws VirtualShelterException
	 */
	public void addSpecies(String speciesName) throws VirtualShelterException;
	
	/**
	 * Deletes a species from database
	 * @param speciesName name of the species to be deleted
	 * @throws VirtualShelterException
	 */
	public void deleteSpecies(String speciesName) throws VirtualShelterException;
	
	/**
	 * Adds a new breed to the database
	 * @param breedName name of the breed to be added
	 * @param speciesName species name of the breed
	 * @throws VirtualShelterException
	 */
	public void addBreed(String breedName, String speciesName) throws VirtualShelterException;
	
	/**
	 * Deletes a breed from the database
	 * @param breedName name of the breed to be deleted
	 * @throws VirtualShelterException
	 */
	public void deleteBreed(String breedName) throws VirtualShelterException;
	
}
