package hu.aut.bme.sportnetwork.api.impl;

import org.springframework.beans.factory.annotation.Autowired;

import hu.bme.aut.sportnetwork.api.RegistrationOperations;
import hu.bme.aut.sportnetwork.dal.UserDAO;
import hu.bme.aut.sportnetwork.entity.Address;
import hu.bme.aut.sportnetwork.entity.Rating;
import hu.bme.aut.sportnetwork.entity.Sports;
import hu.bme.aut.sportnetwork.entity.User;
import hu.bme.aut.sportnetwork.rest.resources.UserArg;

public class RegistrationOperationsImpl implements RegistrationOperations{
	
	@Autowired
	UserDAO userRepository;

	@Override
	public void registrate(UserArg arg) {
		userRepository.saveNewUser((User.toUser(arg)));
	}


}
