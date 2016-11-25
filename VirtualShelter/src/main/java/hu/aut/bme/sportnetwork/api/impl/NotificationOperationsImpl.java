package hu.aut.bme.sportnetwork.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import hu.bme.aut.sportnetwork.api.NotificationOperations;
import hu.bme.aut.sportnetwork.auth.AuthOperations;
import hu.bme.aut.sportnetwork.dal.NotificationDAO;
import hu.bme.aut.sportnetwork.entity.Notification;
import hu.bme.aut.sportnetwork.entity.User;

public class NotificationOperationsImpl implements NotificationOperations {

	@Autowired
	private NotificationDAO notificationRepositroy;
	
	@Autowired
	private AuthOperations authOperations;
	
	@Override
	public List<Notification> getNotifications() {
		User user = authOperations.getLoggedInUser();
		return notificationRepositroy.findByOwnerAndIsDeclined(user, false, new Sort(Sort.Direction.DESC, "send_time"));
	}

}
