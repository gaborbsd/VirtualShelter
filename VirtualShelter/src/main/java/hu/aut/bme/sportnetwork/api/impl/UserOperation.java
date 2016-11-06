package hu.aut.bme.sportnetwork.api.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import hu.bme.aut.sportnetwork.api.IUserOperation;
import hu.bme.aut.sportnetwork.dal.IFriendShipDAO;
import hu.bme.aut.sportnetwork.dal.INotificationDAO;
import hu.bme.aut.sportnetwork.dal.IUserDAO;
import hu.bme.aut.sportnetwork.entity.FriendNotification;
import hu.bme.aut.sportnetwork.entity.FriendShip;
import hu.bme.aut.sportnetwork.entity.Notification;
import hu.bme.aut.sportnetwork.entity.User;

public class UserOperation implements IUserOperation {
	
	@Autowired
	private IUserDAO userRepository;
	
	@Autowired
	private INotificationDAO notificationRepositroy;
	
	@Autowired
	private IFriendShipDAO friendShipRepository;
	
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
			notificationRepositroy.delete(notificationId);
		} else {
			throw new Exception("WRONG NOTIFICATION ID");
		}
		
	}
	

}
