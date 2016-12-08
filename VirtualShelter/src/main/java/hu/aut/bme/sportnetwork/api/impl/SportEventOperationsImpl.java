package hu.aut.bme.sportnetwork.api.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import hu.bme.aut.sportnetwork.api.SportEventOperations;
import hu.bme.aut.sportnetwork.api.SportNetworkException;
import hu.bme.aut.sportnetwork.api.UserOperations;
import hu.bme.aut.sportnetwork.auth.AuthOperations;
import hu.bme.aut.sportnetwork.dal.AddressRepository;
import hu.bme.aut.sportnetwork.dal.CommentRepositroy;
import hu.bme.aut.sportnetwork.dal.NotificationRepository;
import hu.bme.aut.sportnetwork.dal.SportEventRepository;
import hu.bme.aut.sportnetwork.dal.UserRepository;
import hu.bme.aut.sportnetwork.dal.impl.RateParam;
import hu.bme.aut.sportnetwork.entity.Address;
import hu.bme.aut.sportnetwork.entity.Comment;
import hu.bme.aut.sportnetwork.entity.EventStatus;
import hu.bme.aut.sportnetwork.entity.Friend;
import hu.bme.aut.sportnetwork.entity.Notification;
import hu.bme.aut.sportnetwork.entity.NotificationType;
import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.entity.Sports;
import hu.bme.aut.sportnetwork.entity.User;
import hu.bme.aut.sportnetwork.rest.resources.FilterSportEventArg;
import hu.bme.aut.sportnetwork.rest.resources.RateUsersArg;
import hu.bme.aut.sportnetwork.rest.resources.RateWrapper;

public class SportEventOperationsImpl implements SportEventOperations {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AddressRepository addressRepository;

	@Autowired
	CommentRepositroy commentRepository;

	@Autowired
	NotificationRepository notificationRepositroy;
	
	@Autowired
	AuthOperations authOperation;

	@Autowired
	SportEventRepository sportEventRepository;

	UserOperations userOperation;

	@Override
	public List<SportEvent> findAllOpenedEvents() {
		return sportEventRepository.findByIsOpenedAndIsPublic(true, true);
	}

	@Override
	@Transactional
	public SportEvent create(SportEvent e) {

		User owner = authOperation.getLoggedInUser();
		Address address = addressRepository.findByCountryAndCityAndAddress(e.getAddress().getCountry(),
				e.getAddress().getCity(), e.getAddress().getAddress());

		if (address != null) {
			e.setAddress(address);
		}

		e.setOwner(owner);
		e.getMembers().add(owner);
		e.setIsOpened(true);

		SportEvent newEvent = sportEventRepository.save(e);

		setEventStatus(newEvent, owner);
		return newEvent;
		/*
		 * List<FriendShip> usersToNotify =
		 * friendShipRepository.findByListeningUser(owner);
		 * usersToNotify.forEach( f ->
		 * sendEventNotification(f.getUser1().getName().equals(owner.getName())
		 * ? f.getUser2() : f.getUser1(), owner, newEvent,
		 * EventSimpleNotification.NEW_EVENT));
		 */

	}

	@Override
	public List<SportEvent> findEventsByOwner(User owner) {
		return sportEventRepository.findByOwner(owner);
	}

	private void sendEventNotification(User sendTo, User sender, SportEvent e, String message) {
		/*
		 * Notification not = new EventSimpleNotification(sender, e, message);
		 * not.setOwner(sendTo); not.setSendTime(new Date());
		 * sendTo.setHasNotification(true); userRepository.save(sendTo);
		 * notificationRepositroy.save(not);
		 */
	}
	
	private void sendEventRequestNotification(User sendTo, User sender, SportEvent event) {
		/*
		 * EventNotification not = new EventRequestNotification(sender, event);
		 * not.setOwner(sendTo); not.setSendTime(new Date());
		 * sendTo.setHasNotification(true); userRepository.save(sendTo);
		 * notificationRepositroy.save(not);
		 */
	}

	@Override
	public List<SportEvent> listPublicOpenedEvents() {
		return sportEventRepository.findByIsOpenedAndIsPublic(true, true);
	}

	@Override
	@Transactional
	public SportEvent applyToSportEvent(long eventID) throws Exception {
		/*
		 * User applicant = authOperation.getLoggedInUser(); SportEvent event =
		 * eagerFetchSportEvent(eventID);
		 * sendEventRequestNotification(event.getOwner(), applicant, event);
		 * event.setStatus(EventStatus.APPLIED);
		 */
		return null;
	}

	@Override
	@Transactional
	public void acceptEventRequest(long notificationId) throws SportNetworkException {
		/*
		 * Notification not = notificationRepositroy.findOne(notificationId); if
		 * (not instanceof EventRequestNotification) { EventRequestNotification
		 * eventNot = (EventRequestNotification) not; SportEvent event =
		 * eventNot.getEvent(); User applicant = eventNot.getSender(); if
		 * (event.getMaxSize() <= event.getMembers().size()) { throw new
		 * SportNetworkException("NO SPACE"); }
		 * event.setMemberSize(event.getMemberSize() + 1);
		 * event.getMembers().add(applicant); sportEventRepository.save(event);
		 * notificationRepositroy.delete(notificationId); } else { throw new
		 * SportNetworkException("WRONG NOTIFICATION ID"); }
		 */
	}



	@Override
	public SportEvent findById(long id) {
		User user = authOperation.getLoggedInUser();
		SportEvent event = sportEventRepository.findById(id);
		setEventStatus(event, user);
		return event;
	}

	@Override
	@Transactional
	public SportEvent writeComment(long eventID, String comment) {
		/*
		 * User commenter = authOperation.getLoggedInUser(); SportEvent event =
		 * eagerFetchSportEvent(eventID); Comment c = new Comment();
		 * c.setOwner(commenter); c.setEvent(event); c.setDateOfComment(new
		 * Date()); c.setMessage(comment); event.getComments().add(c);
		 * sportEventRepository.save(event);
		 * 
		 * List<User> members = event.getMembers(); members.forEach(m ->
		 * sendEventNotification(m, commenter, event,
		 * EventSimpleNotification.EVENT_COMMENT));
		 * 
		 * setEventStatus(event, commenter); return event;
		 */
		return null;
	}

	@Override
	@Transactional
	public SportEvent deleteComment(long commentID) {
		/*
		 * User deleter = authOperation.getLoggedInUser(); SportEvent event =
		 * commentRepository.findOne(commentID).getEvent(); event =
		 * eagerFetchSportEvent(event.getId()); setEventStatus(event, deleter);
		 * event.getComments().removeIf(c -> c.getId() == commentID);
		 * sportEventRepository.save(event); return event;
		 */
		return null;
	}

	@Override
	public List<SportEvent> filterPublicEvents(FilterSportEventArg arg) throws Exception{
		// return sportEventRepository.filterPublic(toEventFilter(arg));
		return null;
	}
	
	private SportEventFilter toEventFilter(FilterSportEventArg arg) {
		SportEventFilter ret = new SportEventFilter();
		ret.setTitle(arg.getTitle());
		ret.setOwner(arg.getOwner());
		ret.setCity(arg.getCity());
		ret.setLevelFrom(arg.getLevelFrom());
		ret.setLevelTo(arg.getLevelTo());		
		boolean sportNeeded = arg.getText() != null && !arg.getText().isEmpty() && arg.getSport();
		ret.setSport(sportNeeded ?
				Sports.toSport(arg.getText()) : null);
		ret.setText(!arg.getOwner() && !arg.getCity() && !arg.getTitle() ? 
				null : arg.getText());
		return ret;
	}
	
	private void setEventStatus(SportEvent event, User user) {

		if (event.getOwner().getName().equals(user.getName())) {
			event.setStatus(EventStatus.OWNER);
		} else if (event.getMembers().contains(user)) {
			event.setStatus(EventStatus.MEMBER);
		} else if (event.getApplications().stream().anyMatch(a -> a.getName().equals(user.getName()))) {
			event.setStatus(EventStatus.APPLIED);
		} else {
			event.setStatus(EventStatus.NOT_MEMBER);
		}

	}

	@Override
	@Transactional
	public SportEvent cancelEventRequest(long eventID) {
		User canceler = authOperation.getLoggedInUser();
		SportEvent event = eagerFetchSportEvent(eventID);
		// notificationRepositroy.deleteEventRequest(event, canceler);
		// event.setStatus(EventStatus.NOT_MEMBER);
		return event;
	}

	@Override
	@Transactional
	public SportEvent removeUserFromSportEvent(long eventID) {
		/*
		 * User toRemove = authOperation.getLoggedInUser(); SportEvent event =
		 * eagerFetchSportEvent(eventID);
		 * event.setMemberSize(event.getMemberSize() - 1);
		 * event.getMembers().remove(toRemove);
		 * sportEventRepository.save(event);
		 * 
		 * event.setStatus(EventStatus.NOT_MEMBER); return event;
		 */
		return null;
	}

	@Override
	@Transactional
	public SportEvent closeEvent(long eventID) throws Exception {
		/*User owner = authOperation.getLoggedInUser();
		SportEvent event = eagerFetchSportEvent(eventID);
		if (!owner.getName().equals(event.getOwner().getName())) {
			throw new Exception("NOT YOUR EVENT");
		}
		event.setIsOpened(false);
		sportEventRepository.save(event);
		List<User> toNotify = event.getMembers();
		sendEventVoteNotification(owner, event, toNotify);
		event.setStatus(EventStatus.OWNER);
		return event;*/
		return null;
	}

	private void sendEventVoteNotification(User owner, SportEvent event, List<User> toNotify) {
		/*
		 * List<EventRateNotification> notifcications = new ArrayList<>(); for
		 * (User u : toNotify) { EventRateNotification not = new
		 * EventRateNotification(event); not.setOwner(u); not.setSender(owner);
		 * notifcications.add(not); u.setHasNotification(true);
		 * userRepository.save(u); }
		 * notificationRepositroy.save(notifcications);
		 */

	}

	@Override
	public void deleteEvent(long eventID) {
		/*
		 * notificationRepositroy.deleteNotificationsOfEvent(eventID);
		 * sportEventRepository.delete(eventID);
		 */
	}
	
	private SportEvent eagerFetchSportEvent(long id) {
		/*
		 * SportEvent event = sportEventRepository.findOne(id);
		 * event.getMembers().size(); event.getComments().size(); return event;
		 */
		return null;
	}

	private SportEvent getEventByNotification(long id) {
		/*
		 * EventRateNotification not = (EventRateNotification)
		 * notificationRepositroy.findOne(id); SportEvent ret = not.getEvent();
		 * ret.getMembers().size(); return ret;
		 */
		return null;
	}

	@Override
	@Transactional
	public List<User> getMembersOfSportEvent(long notificationId) {
		/*
		 * SportEvent event = getEventByNotification(notificationId); return
		 * event.getMembers();
		 */
		return null;
	}

	@Override
	@Transactional
	public void rateUsers(RateUsersArg arg) throws Exception {
		/*
		 * User user = authOperation.getLoggedInUser(); SportEvent event =
		 * getEventByNotification(arg.getNotificationId()); if
		 * (!event.getMembers().contains(user)) { throw new
		 * Exception("You are not allowed to vote"); }
		 * 
		 * userRepository.rateUsers(toParam(arg), event);
		 * 
		 * notificationRepositroy.delete(arg.getNotificationId());
		 */
	}

	private RateParam toParam(RateUsersArg arg) {
		RateParam param = new RateParam();

		for (RateWrapper wr : arg.getRates()) {
			param.getRates().add(wr.getRate());
			param.getUsers().add(userRepository.findByName(wr.getName()));
		}

		return param;
	}

	@Override
	public List<SportEvent> findFriendEvents() {
		/*
		 * List<User> friends = userOperation.listFriends(); return
		 * sportEventRepository.findByOwnerInAndIsOpened(friends, true);
		 */
		return null;
	}

	@Override
	public List<SportEvent> findMyClosedEvents() {
		/*
		 * User user = authOperation.getLoggedInUser(); return
		 * sportEventRepository.findByMembersAndIsOpened(user, false);
		 */
		return null;
	}

	@Override
	public List<SportEvent> findMyEvents() {
		/*
		 * User user = authOperation.getLoggedInUser(); return
		 * sportEventRepository.findByMembersAndIsOpened(user, true);
		 */
		return null;
	}

	@Override
	public List<SportEvent> findByTitle(String value) {
		/*
		 * List<SportEvent> ret =
		 * sportEventRepository.findByTitleLike(makeLikeValue(value)); if (ret
		 * == null) { ret = new ArrayList<>(); } return ret;
		 */
		return null;
	}

	private String makeLikeValue(String value) {
		return "%" + value.toLowerCase() + "%";
	}

	@Override
	public List<Comment> getCommentsOfEvent(long eventId) {
		/*
		 * SportEvent event = sportEventRepository.findOne(eventId); return
		 * commentRepository.findByEvent(event, new Sort("dateOfComment"));
		 */
		return null;
	}

}
