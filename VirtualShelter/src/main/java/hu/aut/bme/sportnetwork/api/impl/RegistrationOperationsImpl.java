package hu.aut.bme.sportnetwork.api.impl;

import org.springframework.beans.factory.annotation.Autowired;

import hu.bme.aut.sportnetwork.api.RegistrationOperations;
import hu.bme.aut.sportnetwork.dal.UserDAO;
import hu.bme.aut.sportnetwork.entity.User;

public class RegistrationOperationsImpl implements RegistrationOperations{
	
	@Autowired
	UserDAO userRepository;

	@Override
	public User registrate(User u) {
		return userRepository.save(u);
	}

}
