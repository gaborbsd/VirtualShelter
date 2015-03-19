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
	 * @param user user to be promoted to sie administrator
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
	 * @param species species to be added
	 * @throws VirtualShelterException
	 */
	public void addSpecies(String species) throws VirtualShelterException;
	
	/**
	 * Deletes a species from database
	 * @param species species to be deleted
	 * @throws VirtualShelterException
	 */
	public void deleteSpecies(String species) throws VirtualShelterException;
	
	/**
	 * Adds a new breed to the database
	 * @param breed breed to be added
	 * @throws VirtualShelterException
	 */
	public void addBreed(String breed) throws VirtualShelterException;
	
	/**
	 * Deletes a breed from the database
	 * @param breed breed to be deleted
	 * @throws VirtualShelterException
	 */
	public void deleteBreed(String breed) throws VirtualShelterException;
}
