package hu.bme.aut.sportnetwork.dal.impl;

import java.util.List;

import hu.bme.aut.sportnetwork.dal.NotificationDAO;
import hu.bme.aut.sportnetwork.entity.FriendRequestNotification;
import hu.bme.aut.sportnetwork.entity.Notification;
import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.entity.User;

public class NotificationDAOImpl implements NotificationDAO {

	@Override
	public List<Notification> findByOwnerAndIsDeclined(User owner, Boolean isDeclined) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FriendRequestNotification> getFriendRequestSenders(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUserAppliedToEvent(SportEvent e, User u) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteEventRequest(SportEvent e, User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteNotificationsOfEvent(long eventId) {
		// TODO Auto-generated method stub

	}

	@Override
	public FriendRequestNotification isFriendRequestBetween(User u1, User u2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notification save(Notification not) {
		// TODO Auto-generated method stub
		return null;
	}


}
