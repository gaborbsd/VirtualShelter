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
import hu.bme.aut.sportnetwork.entity.FriendStatus;
import hu.bme.aut.sportnetwork.entity.Notification;
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
	@Transactional
	public User findById(long id) {
		User me = authOperations.getLoggedInUser();
		User u = fetchUserWithRatings(id);
		u.setFriendStatus(setFriendStatus(me, u));
		return u;
	}

    @Override
	@Transactional
	public User findByName(String name) {
		return fetchUserWithRatings(name);
	}

	private User fetchUserWithRatings(String name) {
		User ret = userRepository.findByName(name);
		ret.getRatings().size();
		return ret;
	}

	private User fetchUserWithRatings(long id) {
		User ret = userRepository.findOne(id);
		ret.getRatings().size();
		return ret;
	}

	@Override
	@Transactional
	public User sendFriendRequest(String name) {
		User sender = authOperations.getLoggedInUser();
		User u = fetchUserWithRatings(name);
		u.setFriendStatus(FriendStatus.REQUEST_SENT);
		Notification not = new FriendRequestNotification(sender);
		not.setOwner(u);
		not.setSendTime(new Date());
		notificationRepositroy.save(not);
		return u;
	}

	@Override
	@Transactional
	public User acceptFriendRequest(long userId) throws Exception {
		User accepter = authOperations.getLoggedInUser();
		User friend = userRepository.findOne(userId);
		friend.getRatings().size();
		friend.setFriendStatus(FriendStatus.FRIEND);
		FriendRequestNotification not = notificationRepositroy.isFriendRequestBetween(friend, accepter);
		if (not == null) {
			throw new Exception("NO REQUEST");
		}
		return friendAccept(accepter, friend, not);
	}

	@Override
	public User friendAccept(User accepter, User friend, FriendRequestNotification not) {


		FriendShip f = new FriendShip();
		f.setUser1(accepter);
		f.setUser2(friend);
		f.setUser1Listen(true);
		f.setUser2Listen(true);
		friendShipRepository.save(f);

		notificationRepositroy.delete(not.getNotificationId());

		return friend;
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

	@Override
	@Transactional
	public User getCurrent() {
		User u = authOperations.getLoggedInUser();
		u.getRatings().size();
		u.setFriendStatus(FriendStatus.SELF);
		return u;
	}
	
	private FriendStatus setFriendStatus(User self, User other) {
		if (self.getName().equals(other.getName())) {
			return FriendStatus.SELF;
		}

		if (friendShipRepository.getByUser1AndUser2(self, other) != null) {
			return FriendStatus.FRIEND;
		}

		FriendRequestNotification request = notificationRepositroy.isFriendRequestBetween(self, other);

		if (request == null) {
			return FriendStatus.NOT_FRIEND;
		}

		if (request.getOwner().getName().equals(self.getName())) {
			if (request.getIsDeclined()) {
				return FriendStatus.DECLINED;
			}
			return FriendStatus.REQUEST_RECEIVED;
		}

		return FriendStatus.REQUEST_SENT;
	}

	@Override
	@Transactional
	public User cancelFriendRequest(String name) throws Exception {
		User canceler = authOperations.getLoggedInUser();
		User u = fetchUserWithRatings(name);
		u.setFriendStatus(FriendStatus.NOT_FRIEND);
		FriendRequestNotification not = notificationRepositroy.isFriendRequestBetween(canceler, u);
		if (not == null) {
			throw new Exception("NO REQUEST");
		}
		notificationRepositroy.delete(not.getNotificationId());

		return u;
	}

	@Override
	@Transactional
	public User declineFriendRequest(String name) throws Exception {
		User decliner = authOperations.getLoggedInUser();
		User u = fetchUserWithRatings(name);
		u.setFriendStatus(FriendStatus.DECLINED);
		FriendRequestNotification not = notificationRepositroy.isFriendRequestBetween(decliner, u);
		if (not == null) {
			throw new Exception("NO REQUEST");
		}
		not.setIsDeclined(true);
		notificationRepositroy.save(not);

		return u;
	}

	@Override
	@Transactional
	public User deleteFriend(long id) {
		User deleter = authOperations.getLoggedInUser();
		User u = fetchUserWithRatings(id);
		u.setFriendStatus(FriendStatus.NOT_FRIEND);
		FriendShip f = friendShipRepository.getByUser1AndUser2(deleter, u);

		friendShipRepository.delete(f.getId());

		return u;
	}
}
