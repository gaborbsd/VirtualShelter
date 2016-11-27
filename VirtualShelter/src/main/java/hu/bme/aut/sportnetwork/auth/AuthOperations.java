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
		User u = userRepository.findByName(name);
		if (u == null) {
			u = userRepository.findByEmail(name);
		}
		return u;
	}
	
	public String getLoggedInUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
