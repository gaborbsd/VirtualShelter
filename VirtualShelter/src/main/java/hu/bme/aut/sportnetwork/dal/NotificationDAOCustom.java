package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import hu.bme.aut.sportnetwork.entity.FriendNotification;
import hu.bme.aut.sportnetwork.entity.User;

public interface NotificationDAOCustom {

	List<FriendNotification> getFriendRequestSenders(User u);
}
