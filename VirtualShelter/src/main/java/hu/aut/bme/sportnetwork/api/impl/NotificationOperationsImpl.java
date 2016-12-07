package hu.aut.bme.sportnetwork.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import hu.bme.aut.sportnetwork.api.NotificationOperations;
import hu.bme.aut.sportnetwork.api.SportNetworkException;
import hu.bme.aut.sportnetwork.api.UserOperations;
import hu.bme.aut.sportnetwork.auth.AuthOperations;
import hu.bme.aut.sportnetwork.dal.NotificationRepository;
import hu.bme.aut.sportnetwork.dal.UserRepository;
import hu.bme.aut.sportnetwork.entity.Notification;

public class NotificationOperationsImpl implements NotificationOperations {

	@Autowired
	private NotificationRepository notificationRepositroy;
	
	@Autowired
	private AuthOperations authOperations;

	@Autowired
	private UserRepository userRepository;

	private UserOperations userOperation;

	@Override
	@Transactional
	public List<Notification> getNotifications() {
		/*
		 * User user = authOperations.getLoggedInUser();
		 * user.setHasNotification(false); userRepository.save(user); return
		 * notificationRepositroy.findByOwnerAndIsDeclined(user, false, new
		 * Sort(Sort.Direction.DESC, "sendTime"));
		 */
		return null;
	}

	@Override
	@Transactional
	public List<Notification> declineRequest(long id) throws SportNetworkException {
		/*
		 * User user = authOperations.getLoggedInUser(); Notification not =
		 * notificationRepositroy.findOne(id); if
		 * (!not.getOwner().getName().equals(user.getName())) { throw new
		 * SportNetworkException("NOT YOUR NOTIFICATION"); }
		 * not.setIsDeclined(true); notificationRepositroy.save(not); return
		 * notificationRepositroy.findByOwnerAndIsDeclined(user, false, new
		 * Sort(Sort.Direction.DESC, "sendTime"));
		 */
		return null;
	}

	@Override
	public void deleteNotification(long id) throws SportNetworkException {
		/*
		 * User user = authOperations.getLoggedInUser(); Notification not =
		 * notificationRepositroy.findOne(id); if
		 * (!not.getOwner().getName().equals(user.getName())) { throw new
		 * SportNetworkException("NOT YOUR NOTIFICATION"); }
		 * notificationRepositroy.delete(id);
		 */
	}

	@Override
	@Transactional
	public void friendAccept(long notificationId) throws SportNetworkException {
		/*
		 * Notification not = notificationRepositroy.findOne(notificationId);
		 * FriendRequestNotification friendNot = null; try { friendNot =
		 * (FriendRequestNotification) not; } catch (Exception e) { throw new
		 * SportNetworkException("WRONG NOT ID"); }
		 * 
		 * User accepter = authOperations.getLoggedInUser();
		 * 
		 * if (!accepter.getName().equals(not.getOwner().getName())) { throw new
		 * SportNetworkException("NOT YOUR NOTIFICATION"); }
		 * 
		 * User friend = not.getSender();
		 * 
		 * userOperation.friendAccept(accepter, friend, friendNot);
		 */
	}

}
