package hu.aut.bme.sportnetwork.api.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import hu.bme.aut.sportnetwork.api.UserOperations;
import hu.bme.aut.sportnetwork.dal.FriendShipDAO;
import hu.bme.aut.sportnetwork.dal.NotificationDAO;
import hu.bme.aut.sportnetwork.dal.UserDAO;
import hu.bme.aut.sportnetwork.entity.FriendNotification;
import hu.bme.aut.sportnetwork.entity.FriendShip;
import hu.bme.aut.sportnetwork.entity.Notification;
import hu.bme.aut.sportnetwork.entity.NotificationStatus;
import hu.bme.aut.sportnetwork.entity.User;

public class UserOperationsImpl implements UserOperations {
	
	@Autowired
	private UserDAO userRepository;
	
	@Autowired
	private NotificationDAO notificationRepositroy;
	
	@Autowired
	private FriendShipDAO friendShipRepository;
	
    @Override
	public User findById(long id) {
		return userRepository.findOne(id);
	}

    @Override
	public User findByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public void sendFriendRequest(String name, String message) {
		User sender = findByName("Andras");
		User u = findByName(name);
		Notification not = new FriendNotification(sender);
		not.setOwner(u);
		not.setMessage(message);
		not.setStatus(NotificationStatus.SENT);
		not.setSendTime(new Date());
		notificationRepositroy.save(not);
	}

	@Override
	@Transactional
	public void acceptFriendRequest(long notificationId) throws Exception {		
		Notification not = notificationRepositroy.findOne(notificationId);
		User accepter = not.getOwner();
		if (not instanceof FriendNotification) {
			FriendNotification friendNot = (FriendNotification) not;
			User friend = friendNot.getUser();
			FriendShip f1 = new FriendShip();
			f1.setPerson(accepter);
			f1.setFriend(friend);
			f1.setListenNotifications(true);
			FriendShip f2 = new FriendShip();
			f2.setFriend(accepter);
			f2.setPerson(friend);
			f2.setListenNotifications(true);
			friendShipRepository.save(f1);
			friendShipRepository.save(f2);
			
			not.setStatus(NotificationStatus.ACCEPTED);
			not.setModificationTime(new Date());
			notificationRepositroy.save(not);
		} else {
			throw new Exception("WRONG NOTIFICATION ID");
		}
		
	}

	@Override
	public List<User> listFriends() {
		return userRepository.getFriendsOfUser("Andras");
	}

	@Override
	public List<User> listFriendRequest() {
		User u = userRepository.findByName("Andras");
		List<FriendNotification> nots = notificationRepositroy.getFriendRequestSenders(u);
		List<User> ret = new ArrayList<>();
		nots.forEach(n -> ret.add(n.getUser()));
		return ret;
	}
	

}
