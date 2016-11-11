package hu.bme.aut.sportnetwork.api;

import hu.bme.aut.sportnetwork.entity.User;

public interface UserOperations {
	
	User findById(long id);
	
	User findByName(String name);
	
	void sendFriendRequest(String name, String message);
	
	void acceptFriendRequest(long notificationId) throws Exception;
}
