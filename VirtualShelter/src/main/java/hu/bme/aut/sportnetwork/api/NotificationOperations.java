package hu.bme.aut.sportnetwork.api;

import java.util.List;

import hu.bme.aut.sportnetwork.entity.Notification;

public interface NotificationOperations {

	List<Notification> getNotifications();

	List<Notification> declineRequest(long id) throws SportNetworkException;

	void deleteNotification(long id) throws SportNetworkException;

	void friendAccept(long notificationId) throws SportNetworkException;
}
