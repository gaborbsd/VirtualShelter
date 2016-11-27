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
import hu.bme.aut.sportnetwork.entity.Rating;
import hu.bme.aut.sportnetwork.entity.User;
import hu.bme.aut.sportnetwork.rest.resources.UserArg;

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
		User u = userRepository.findOne(id);
		u.setFriendStatus(setFriendStatus(me, u));
		return u;
	}

    @Override
	@Transactional
	public User findByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	@Transactional
	public User sendFriendRequest(String name) {
		User sender = authOperations.getLoggedInUser();
		User u = userRepository.findByName(name);
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
		User u = authOperations.getLoggedInUser();
		List<FriendShip> fl = friendShipRepository.getByUser1OrUser2(u, u);

		List<User> ret = new ArrayList<>();

		fl.forEach(f -> ret.add(f.getUser1().getName().equals(u.getName()) ? f.getUser2() : f.getUser1()));

		return ret;
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
		User u = userRepository.findByName(name);
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
		User u = userRepository.findByName(name);
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
		User u = userRepository.findOne(id);
		u.setFriendStatus(FriendStatus.NOT_FRIEND);
		FriendShip f = friendShipRepository.getByUser1AndUser2(deleter, u);

		friendShipRepository.delete(f.getId());

		return u;
	}

	@Override
	@Transactional
	public User modify(UserArg arg) throws Exception {
		User current = authOperations.getLoggedInUser();
		List<Rating> ratings = current.getRatings();
		if (!current.getName().equals(arg.getName())) {
			throw new Exception("NO RIGHT FOR THIS OPERATION");
		}
		User modified = User.toUser(arg);

		for (Rating rating : modified.getRatings()) {
			if (ratings.stream().allMatch(r -> r.getSport() != rating.getSport())) {
				ratings.add(rating);
			}
		}

		modified.setRatings(ratings);
		modified.setId(current.getId());

		User saved = userRepository.modifyUser(modified);

		saved.getRatings().size();
		saved.setFriendStatus(FriendStatus.SELF);
		return saved;
		// userRepository.modifyUser(modified);

	}

	@Override
	public List<User> search(String text) {
		return userRepository.search(text.toLowerCase());
	}
}
