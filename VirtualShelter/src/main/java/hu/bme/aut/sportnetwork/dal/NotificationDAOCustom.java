package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import hu.bme.aut.sportnetwork.entity.FriendRequestNotification;
import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.entity.User;

public interface NotificationDAOCustom {

	List<FriendRequestNotification> getFriendRequestSenders(User u);
	
	boolean isUserAppliedToEvent(SportEvent e, User u);
}
