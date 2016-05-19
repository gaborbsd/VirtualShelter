package hu.aut.bme.sportnetwork.api.impl;

import org.springframework.beans.factory.annotation.Autowired;

import hu.bme.aut.sportnetwork.api.IRegistrationOperation;
import hu.bme.aut.sportnetwork.dal.UserRepository;
import hu.bme.aut.sportnetwork.entity.User;

public class RegistrationOperation implements IRegistrationOperation{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User registrate(User u) {
		return userRepository.save(u);
	}

}
