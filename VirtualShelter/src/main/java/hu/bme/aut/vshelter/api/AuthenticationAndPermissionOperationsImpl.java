package hu.bme.aut.vshelter.api;

import javax.inject.Inject;

import hu.bme.aut.vshelter.dal.UserFacade;
import hu.bme.aut.vshelter.entity.User;

public class AuthenticationAndPermissionOperationsImpl implements
		IAuthenticationAndPermissionOperations {
	@Inject
	private UserFacade userFacade;
	
	@Override
	public boolean authenticate(String email, String password) throws VirtualShelterException {
		User u = userFacade.getUserByEmail(email);
		return u.getPassword().equals(password) ? true : false;
	}

}
