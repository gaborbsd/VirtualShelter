package hu.aut.bme.sportnetwork.api.impl;

import org.springframework.beans.factory.annotation.Autowired;

import hu.bme.aut.sportnetwork.api.IUserOperation;
import hu.bme.aut.sportnetwork.dal.UserRepository;
import hu.bme.aut.sportnetwork.entity.User;

public class UserOperation implements IUserOperation {
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public User findById(long id) {
		return userRepository.findOne(id);
	}


	@Override
	public User findByName(String name) {
		return userRepository.getUserByName(name);
	}

}
