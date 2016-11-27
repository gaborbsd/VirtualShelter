package hu.bme.aut.sportnetwork.api;

import java.util.List;

import hu.bme.aut.sportnetwork.entity.FriendRequestNotification;
import hu.bme.aut.sportnetwork.entity.User;
import hu.bme.aut.sportnetwork.rest.resources.UserArg;

public interface UserOperations {
	
	User findById(long id);
	
	User findByName(String name);
	
	User sendFriendRequest(String name);
	
	User acceptFriendRequest(long userId) throws Exception;
	
	List<User> listFriends();
	
	List<User> listFriendRequest();

	boolean pollNotification();

	User getCurrent();

	User friendAccept(User accepter, User friend, FriendRequestNotification not);

	User cancelFriendRequest(String name) throws Exception;

	User declineFriendRequest(String name) throws Exception;

	User deleteFriend(long id);

	User modify(UserArg arg) throws Exception;

	List<User> search(String text);

	void warnUser(String name, String message);

	void deleteUser(Long id);
}
