package hu.bme.aut.sportnetwork.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import hu.bme.aut.sportnetwork.dal.UserDAO;
import hu.bme.aut.sportnetwork.entity.User;

public class AuthOperations {

	@Autowired
	private UserDAO userRepository;
	
	public User getLoggedInUser() {
		String name = getLoggedInUserName();
		return userRepository.findByName(name);
	}
	
	public String getLoggedInUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
