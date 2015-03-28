package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.entity.User;

import java.util.List;

public interface UserFacade {
	
	User findUserById(int userId);
	
	List<User> findAll();
	
	void create(User user);
	
	void edit(User user);
	
	void deleteUserById(int userId);
	
	/**
	 * Adds site-administrator role to the user with the given id
	 * @param userId
	 */
	void promoteUserToSiteAdministrator(int userId);
	
	/**
	 * Removes site-administrator role from user with the given id
	 * @param userId
	 */
	void revokeUserFromSiteAdministrator(int userId);
	
	/**
	 * Returns the userId from the owner of the given email
	 * @return
	 * @throws VirtualShelterException
	 */
	public int getUserIdfromEmail(String email) throws VirtualShelterException;

}
