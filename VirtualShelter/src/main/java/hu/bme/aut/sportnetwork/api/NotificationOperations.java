package hu.bme.aut.sportnetwork.api;

import java.util.List;

import hu.bme.aut.sportnetwork.entity.Notification;

public interface NotificationOperations {

	List<Notification> getNotifications();
}
