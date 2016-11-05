package hu.bme.aut.sportnetwork.api;

import hu.bme.aut.sportnetwork.entity.User;

public interface IUserOperation {
	
	User findById(long id);
	
	User findByName(String name);
	
	void sendFriendRequest(String name, String message);
	
	void acceptFriendRequest(long id) throws Exception;
}
