package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.entity.User;


public interface UserFacade extends AbstractFacade<User>{
	
	/**
	 * Adds site-administrator role to the user with the given id
	 * @param userId
	 */
	void promoteUserToSiteAdministrator(long userId) throws VirtualShelterException;
	
	/**
	 * Removes site-administrator role from user with the given id
	 * @param userId
	 */
	void revokeUserFromSiteAdministrator(long userId) throws VirtualShelterException;
	
	/**
	 * Returns the userId from the owner of the given email
	 * @return
	 * @throws VirtualShelterException
	 */
	public long getUserIdfromEmail(String email) throws VirtualShelterException;
	

}
