package hu.bme.aut.sportnetwork.dal;

import hu.bme.aut.sportnetwork.entity.User;

public interface UserRepositoryCustom {
	
	public User getUserByName(String name);
	
}
