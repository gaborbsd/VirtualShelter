package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import hu.bme.aut.sportnetwork.entity.User;

public interface UserDAOCustom {
	
	List<User> getFriendsOfUser(String name);
	
}
