package hu.aut.bme.sportnetwork.api.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import hu.bme.aut.sportnetwork.api.UserOperations;
import hu.bme.aut.sportnetwork.auth.AuthOperations;
import hu.bme.aut.sportnetwork.dal.FriendShipDAO;
import hu.bme.aut.sportnetwork.dal.NotificationDAO;
import hu.bme.aut.sportnetwork.dal.UserDAO;
import hu.bme.aut.sportnetwork.entity.FriendRequestNotification;
import hu.bme.aut.sportnetwork.entity.FriendShip;
import hu.bme.aut.sportnetwork.entity.Notification;
import hu.bme.aut.sportnetwork.entity.EventNotification;
import hu.bme.aut.sportnetwork.entity.User;

public class UserOperationsImpl implements UserOperations {
	
	@Autowired
	private UserDAO userRepository;
	
	@Autowired
	private NotificationDAO notificationRepositroy;
	
	@Autowired
	private FriendShipDAO friendShipRepository;
	
	@Autowired
	private AuthOperations authOperations;
	
    @Override
	public User findById(long id) {
		return userRepository.findOne(id);
	}

    @Override
	public User findByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public void sendFriendRequest(String name) {
		User sender = authOperations.getLoggedInUser();
		User u = findByName(name);
		Notification not = new FriendRequestNotification(sender);
		not.setOwner(u);
		not.setSendTime(new Date());
		notificationRepositroy.save(not);
	}

	@Override
	@Transactional
	public void acceptFriendRequest(long notificationId) throws Exception {		
		Notification not = notificationRepositroy.findOne(notificationId);
		User accepter = not.getOwner();
		if (not instanceof FriendRequestNotification) {
			FriendRequestNotification friendNot = (FriendRequestNotification) not;
			User friend = friendNot.getSender();
			FriendShip f = new FriendShip();
			f.setUser1(accepter);
			f.setUser2(friend);
			f.setUser1Listen(true);
			f.setUser2Listen(true);
			friendShipRepository.save(f);
			
			notificationRepositroy.delete(notificationId);
		} else {
			throw new Exception("WRONG NOTIFICATION ID");
		}
		
	}

	@Override
	public List<User> listFriends() {
		return userRepository.getFriendsOfUser(authOperations.getLoggedInUserName());
	}

	@Override
	public List<User> listFriendRequest() {
		User u = authOperations.getLoggedInUser();
		List<FriendRequestNotification> nots = notificationRepositroy.getFriendRequestSenders(u);
		List<User> ret = new ArrayList<>();
		nots.forEach(n -> ret.add(n.getSender()));
		return ret;
	}

	@Override
	public boolean pollNotification() {
		User user = authOperations.getLoggedInUser();
		return user.getHasNotification();
	}
	

}
