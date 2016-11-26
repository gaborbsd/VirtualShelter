package hu.bme.aut.sportnetwork.api;

import java.util.List;

import hu.bme.aut.sportnetwork.entity.FriendRequestNotification;
import hu.bme.aut.sportnetwork.entity.User;

public interface UserOperations {
	
	User findById(long id);
	
	User findByName(String name);
	
	void sendFriendRequest(String name);
	
	User acceptFriendRequest(long userId) throws Exception;
	
	List<User> listFriends();
	
	List<User> listFriendRequest();

	boolean pollNotification();

	User getCurrent();

	User friendAccept(User accepter, User friend, FriendRequestNotification not);
}
