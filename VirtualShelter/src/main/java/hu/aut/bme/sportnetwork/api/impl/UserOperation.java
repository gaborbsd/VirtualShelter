package hu.aut.bme.sportnetwork.api.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import hu.bme.aut.sportnetwork.api.IUserOperation;
import hu.bme.aut.sportnetwork.dal.INotificationDAO;
import hu.bme.aut.sportnetwork.dal.IUserDAO;
import hu.bme.aut.sportnetwork.entity.FriendNotification;
import hu.bme.aut.sportnetwork.entity.Notification;
import hu.bme.aut.sportnetwork.entity.User;

public class UserOperation implements IUserOperation {
	
	@Autowired
	private IUserDAO userRepository;
	
	@Autowired
	private INotificationDAO notificationRepositroy;
	
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

}
