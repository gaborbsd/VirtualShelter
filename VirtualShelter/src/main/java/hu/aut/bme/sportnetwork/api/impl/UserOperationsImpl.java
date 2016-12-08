package hu.aut.bme.sportnetwork.api.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import hu.bme.aut.sportnetwork.api.SportNetworkException;
import hu.bme.aut.sportnetwork.api.UserOperations;
import hu.bme.aut.sportnetwork.auth.AuthOperations;
import hu.bme.aut.sportnetwork.dal.NotificationRepository;
import hu.bme.aut.sportnetwork.dal.UserRepository;
import hu.bme.aut.sportnetwork.entity.Friend;
import hu.bme.aut.sportnetwork.entity.FriendRequest;
import hu.bme.aut.sportnetwork.entity.FriendStatus;
import hu.bme.aut.sportnetwork.entity.Notification;
import hu.bme.aut.sportnetwork.entity.User;
import hu.bme.aut.sportnetwork.rest.resources.UserArg;

public class UserOperationsImpl implements UserOperations {

	@Autowired
	private NotificationRepository notificationRepositroy;
	
	@Autowired
	private AuthOperations authOperations;
	
	@Autowired
	private UserRepository userRepository;

    @Override
	public User findById(long id) {
		User self = authOperations.getLoggedInUser();
		User u = userRepository.findOne(id);
		u.setFriendStatus(setFriendStatus(self, u));
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
		/*
		 * User sender = authOperations.getLoggedInUser(); User u =
		 * userRepository.findByName(name); u.setHasNotification(true);
		 * u.setFriendStatus(FriendStatus.REQUEST_SENT); Notification not = new
		 * FriendRequestNotification(sender); not.setOwner(u);
		 * not.setSendTime(new Date()); notificationRepositroy.save(not); return
		 * u;
		 */
		return null;
	}

	@Override
	@Transactional
	public User acceptFriendRequest(long userId) throws SportNetworkException {
		/*
		 * User accepter = authOperations.getLoggedInUser(); User friend =
		 * userRepository.findOne(userId); friend.getRatings().size();
		 * friend.setFriendStatus(FriendStatus.FRIEND);
		 * FriendRequestNotification not =
		 * notificationRepositroy.isFriendRequestBetween(friend, accepter); if
		 * (not == null) { throw new SportNetworkException("NO REQUEST"); }
		 * return friendAccept(accepter, friend, not);
		 */
		return null;
	}

	@Override
	public User friendAccept(User accepter, User friend, Notification not) {


		/*
		 * FriendShip f = new FriendShip(); f.setUser1(accepter);
		 * f.setUser2(friend); f.setUser1Listen(true); f.setUser2Listen(true);
		 * friendShipRepository.save(f);
		 * 
		 * notificationRepositroy.delete(not.getNotificationId());
		 * 
		 * return friend;
		 */
		return null;
	}

	@Override
	public List<User> listFriends() {
		User u = authOperations.getLoggedInUser();

		List<User> ret = new ArrayList<>();

		// fl.forEach(f -> ret.add(f.getUser1().getName().equals(u.getName()) ?
		// f.getUser2() : f.getUser1()));

		return ret;
	}

	@Override
	public boolean pollNotification() {
		return userRepository.getHasNotificationByName(authOperations.getLoggedInUserName());
	}

	@Override
	@Transactional
	public User getCurrent() {

		User u = authOperations.getLoggedInUser();
		u.setFriendStatus(FriendStatus.SELF);
		return u;
	}
	
	private FriendStatus setFriendStatus(User self, User other) {

		if (self.getName().equals(other.getName())) {
			return FriendStatus.SELF;
		}

		if (self.getFriends().stream().anyMatch(f -> f.getFriend().getName().equals(other.getName()))) {
			return FriendStatus.FRIEND;
		}

		if (self.getFriendRequests().stream().anyMatch(r -> r.getReceiver().getName().equals(other.getName()))) {
			return FriendStatus.REQUEST_SENT;
		}

		Optional<FriendRequest> opt = self.getFriendRequests().stream()
				.filter(r -> r.getSender().getName().equals(other.getName())).findFirst();

		if (opt.isPresent()) {
			if (opt.get().getIsDeclined()) {
				return FriendStatus.DECLINED;
			}
			return FriendStatus.REQUEST_RECEIVED;
		}

		return FriendStatus.NOT_FRIEND;
	}

	@Override
	@Transactional
	public User cancelFriendRequest(String name) throws SportNetworkException {
		/*
		 * User canceler = authOperations.getLoggedInUser(); User u =
		 * userRepository.findByName(name); //
		 * u.setFriendStatus(FriendStatus.NOT_FRIEND); FriendRequestNotification
		 * not = notificationRepositroy.isFriendRequestBetween(canceler, u); if
		 * (not == null) { throw new SportNetworkException("NO REQUEST"); } //
		 * notificationRepositroy.delete(not.getNotificationId());
		 * 
		 * return u;
		 */
		return null;
	}

	@Override
	@Transactional
	public User declineFriendRequest(String name) throws SportNetworkException {
		/*
		 * User decliner = authOperations.getLoggedInUser(); User u =
		 * userRepository.findByName(name);
		 * u.setFriendStatus(FriendStatus.DECLINED); FriendRequestNotification
		 * not = notificationRepositroy.isFriendRequestBetween(decliner, u); if
		 * (not == null) { throw new SportNetworkException("NO REQUEST"); }
		 * not.setIsDeclined(true); notificationRepositroy.save(not);
		 * 
		 * return u;
		 */
		return null;
	}

	@Override
	@Transactional
	public User deleteFriend(long id) {
		/*
		 * User deleter = authOperations.getLoggedInUser(); User u =
		 * userRepository.findOne(id);
		 * u.setFriendStatus(FriendStatus.NOT_FRIEND); FriendShip f =
		 * friendShipRepository.getByUser1AndUser2(deleter, u);
		 * 
		 * friendShipRepository.delete(f.getId());
		 * 
		 * return u;
		 */
		return null;
	}

	@Override
	@Transactional
	public User modify(UserArg arg) throws SportNetworkException {
		/*
		 * User current = authOperations.getLoggedInUser(); if
		 * (!current.getName().equals(arg.getName())) { throw new
		 * SportNetworkException("NO RIGHT FOR THIS OPERATION"); }
		 * 
		 * User u = userRepository.findByEmail(arg.getEmail()); if (u != null &&
		 * !current.getEmail().equals(u.getEmail())) { throw new
		 * SportNetworkException("EMAIL ALREADY EXISTS"); }
		 * 
		 * List<Rating> ratings = current.getRatings();
		 * 
		 * User modified = User.toUser(arg);
		 * 
		 * for (Rating rating : modified.getRatings()) { if
		 * (ratings.stream().allMatch(r -> r.getSport() != rating.getSport())) {
		 * ratings.add(rating); } }
		 * 
		 * modified.setRatings(ratings); modified.setId(current.getId());
		 * 
		 * User saved = userRepository.modifyUser(modified);
		 * 
		 * saved.getRatings().size(); saved.setFriendStatus(FriendStatus.SELF);
		 * return saved;
		 */
		return null;
		// userRepository.modifyUser(modified);

	}

	@Override
	public List<User> search(String text) {
		return null;
	}

	@Override
	@Transactional
	public void warnUser(String name, String message) {
		/*
		 * User u = userRepository.findByName(name); u.setHasWarning(true);
		 * u.setWarningMessage(message); userRepository.save(u);
		 */
	}

	@Override
	public void deleteUser(Long id) {
		/*
		 * User u = userRepository.findOne(id); u.setDeleted(true);
		 * userRepository.save(u);
		 */

	}
}
