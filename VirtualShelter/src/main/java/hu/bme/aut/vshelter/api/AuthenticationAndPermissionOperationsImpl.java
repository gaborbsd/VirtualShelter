package hu.bme.aut.vshelter.api;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;

import hu.bme.aut.vshelter.dal.UserFacade;
import hu.bme.aut.vshelter.dal.UserRepository;
import hu.bme.aut.vshelter.entity.User;

public class AuthenticationAndPermissionOperationsImpl implements
		IAuthenticationAndPermissionOperations {

// Before Spring Data we used these Facades
//	@Inject
//	private UserFacade userFacade;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public boolean authenticate(String email, String password) throws VirtualShelterException {
		User u = userRepository.getUserByEmail(email);
		return u.getPassword().equals(password) ? true : false;
	}

}
