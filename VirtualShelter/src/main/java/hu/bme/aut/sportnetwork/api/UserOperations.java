package hu.bme.aut.sportnetwork.api;

import java.util.List;

import hu.bme.aut.sportnetwork.entity.Notification;
import hu.bme.aut.sportnetwork.entity.User;
import hu.bme.aut.sportnetwork.rest.resources.UserArg;

public interface UserOperations {
	
	User findById(long id);
	
	User findByName(String name);
	
	User sendFriendRequest(String name);
	
	User acceptFriendRequest(long userId) throws SportNetworkException;
	
	List<User> listFriends();

	boolean pollNotification();

	User getCurrent();

	User friendAccept(User accepter, User friend, Notification not);

	User cancelFriendRequest(String name) throws SportNetworkException;

	User declineFriendRequest(String name) throws SportNetworkException;

	User deleteFriend(long id);

	User modify(UserArg arg) throws SportNetworkException;

	List<User> search(String text);

	void warnUser(String name, String message);

	void deleteUser(Long id);
}
