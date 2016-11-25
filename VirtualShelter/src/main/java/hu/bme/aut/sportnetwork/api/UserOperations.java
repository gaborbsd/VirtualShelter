package hu.bme.aut.sportnetwork.api;

import java.util.List;

import hu.bme.aut.sportnetwork.entity.User;

public interface UserOperations {
	
	User findById(long id);
	
	User findByName(String name);
	
	void sendFriendRequest(String name);
	
	void acceptFriendRequest(long notificationId) throws Exception;
	
	List<User> listFriends();
	
	List<User> listFriendRequest();

	boolean pollNotification();
}
