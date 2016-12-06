package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import hu.bme.aut.sportnetwork.entity.FriendRequestNotification;
import hu.bme.aut.sportnetwork.entity.Notification;
import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.entity.User;

public interface NotificationDAO {
	
	List<Notification> findByOwnerAndIsDeclined(User owner, Boolean isDeclined);

	List<FriendRequestNotification> getFriendRequestSenders(User u);

	boolean isUserAppliedToEvent(SportEvent e, User u);

	void deleteEventRequest(SportEvent e, User u);

	void deleteNotificationsOfEvent(long eventId);

	FriendRequestNotification isFriendRequestBetween(User u1, User u2);

	Notification save(Notification not);
}
