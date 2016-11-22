package hu.aut.bme.sportnetwork.api.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import hu.bme.aut.sportnetwork.api.SportEventOperations;
import hu.bme.aut.sportnetwork.dal.AddressDAO;
import hu.bme.aut.sportnetwork.dal.CommentDAO;
import hu.bme.aut.sportnetwork.dal.FriendShipDAO;
import hu.bme.aut.sportnetwork.dal.NotificationDAO;
import hu.bme.aut.sportnetwork.dal.SportEventDAO;
import hu.bme.aut.sportnetwork.dal.UserDAO;
import hu.bme.aut.sportnetwork.entity.Address;
import hu.bme.aut.sportnetwork.entity.Comment;
import hu.bme.aut.sportnetwork.entity.EventNotification;
import hu.bme.aut.sportnetwork.entity.EventRequestNotification;
import hu.bme.aut.sportnetwork.entity.EventStatus;
import hu.bme.aut.sportnetwork.entity.FriendRequestNotification;
import hu.bme.aut.sportnetwork.entity.FriendShip;
import hu.bme.aut.sportnetwork.entity.Notification;
import hu.bme.aut.sportnetwork.entity.NotificationStatus;
import hu.bme.aut.sportnetwork.entity.RequestNotification;
import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.entity.Sports;
import hu.bme.aut.sportnetwork.entity.User;
import hu.bme.aut.sportnetwork.rest.resources.FilterSportEventArg;

public class SportEventOperationsImpl implements SportEventOperations {
	
	@Autowired
	SportEventDAO sportEventRepository;
	
	@Autowired
	UserDAO userRepository;
	
	@Autowired
	CommentDAO commentRepository;
	
	@Autowired
	FriendShipDAO friendShipRepository;
	
	@Autowired
	NotificationDAO notificationRepositroy;
	
	private static final String NEW_EVENT = "NEW EVENT CREATED";
	
	private static final String EVENT_COMMENT = "COMMENT ADDED";

	@Override
	public List<SportEvent> findAllOpenedEvents() {
		return sportEventRepository.findByIsOpened(true);
	}

	@Override
	@Transactional
	public SportEvent create(SportEvent e) {
		//User owner = e.getOwner();
		User owner = userRepository.findByName("Andras");
		e.setOwner(owner);
		e.getMembers().add(owner);
		e.setMemberSize(e.getMembers().size());
		e.setIsOpened(true);
		SportEvent newEvent = sportEventRepository.saveNewEvent(e);
		List<Object> usersToNotify = friendShipRepository.findByPersonAndListenNotifications(owner, true);
		usersToNotify.forEach(u -> sendEventNotification((User)u, owner, newEvent, NEW_EVENT));
		newEvent.setStatus(EventStatus.OWNER);
		return newEvent;
	}

	@Override
	public List<SportEvent> findEventsByOwner(User owner) {
		return sportEventRepository.findByOwner(owner);
	}

	private void sendEventNotification(User sendTo, User sender, SportEvent e, String message) {
		Notification not = new EventNotification(sender, e);
		not.setOwner(sendTo);
		not.setSendTime(new Date());
		not.setMessage(message);
		notificationRepositroy.save(not);
	}
	
	private void sendEventRequestNotification(User sendTo, User sender, SportEvent event) {
		RequestNotification not = new EventRequestNotification(sender, event);
		not.setOwner(sendTo);
		not.setSendTime(new Date());
		not.setMessage(sender.getName() + " APPLIED TO THIS EVENT");
		not.setStatus(NotificationStatus.SENT);
		notificationRepositroy.save(not);
	}

	@Override
	public List<SportEvent> listPublicOpenedEvents() {
		return sportEventRepository.findByIsPublic(true, new Sort("date"));
	}

	@Override
	@Transactional
	public SportEvent applyToSportEvent(long eventID) throws Exception {
		User applicant = userRepository.findByName("Andras");
		SportEvent event = eagerFetchSportEvent(eventID);
		sendEventRequestNotification(event.getOwner(), applicant, event);
		event.setStatus(EventStatus.APPLIED);
		return event;
	}

	@Override
	@Transactional
	public void acceptEventRequest(long notificationId) throws Exception{		
		Notification not = notificationRepositroy.findOne(notificationId);
		//User accepter = not.getOwner();
		if (not instanceof EventRequestNotification) {
			EventRequestNotification eventNot = (EventRequestNotification) not;
			SportEvent event = eventNot.getEvent();
			User applicant = eventNot.getSender();
			if (event.getMaxSize() <= event.getMembers().size()) {
				throw new Exception("NO SPACE");
			}
			event.getMembers().add(applicant);
			eventNot.setStatus(NotificationStatus.ACCEPTED);
			eventNot.setModificationTime(new Date());
			sportEventRepository.save(event);
			notificationRepositroy.save(eventNot);
		} else {
			throw new Exception("WRONG NOTIFICATION ID");
		}
		
	}

	@Override
	public void rateUser(long eventID, long userId, int value) {
		/*SportEvent event = sportEventRepository.findOne(eventID);
		User toRate = event.getMembers().stream().filter(u -> u.getId() == userId).findFirst().get();
		toRate.get*/
	}

	@Override
	@Transactional
	public SportEvent findById(long id) {
		User user = userRepository.findByName("Andras");
		SportEvent ret = eagerFetchSportEvent(id);
		setEventStatus(ret, user);
		return ret;
	}

	@Override
	@Transactional
	public SportEvent writeComment(long eventID, String comment) {
		User commenter = userRepository.findByName("Andras");
		SportEvent event = eagerFetchSportEvent(eventID);
		Comment c = new Comment();
		c.setOwner(commenter);
		c.setEvent(event);
		c.setDateOfComment(new Date());
		c.setMessage(comment);
		event.getComments().add(c);
		sportEventRepository.save(event);
		
		List<User> members = event.getMembers();
		members.forEach(m -> sendEventNotification(m, commenter, event, EVENT_COMMENT));

		setEventStatus(event, commenter);
		return event;
	}

	@Override
	@Transactional
	public SportEvent deleteComment(long commentID) {
		User commenter = userRepository.findByName("Andras");
		SportEvent event = commentRepository.findOne(commentID).getEvent();
		event = eagerFetchSportEvent(event.getId());
		setEventStatus(event, commenter);
		event.getComments().removeIf(c -> c.getId() == commentID);
		sportEventRepository.save(event);
		return event;
	}

	@Override
	public List<SportEvent> filterPublicEvents(FilterSportEventArg arg) throws Exception{
		return sportEventRepository.filterPublic(toEventFilter(arg));
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
		} else if (notificationRepositroy.isUserAppliedToEvent(event, user)) {
			event.setStatus(EventStatus.APPLIED);
		} else {
			event.setStatus(EventStatus.NOT_MEMBER);
		}	
	}

	@Override
	@Transactional
	public SportEvent cancelEventRequest(long eventID) {
		User applicant = userRepository.findByName("Andras");
		SportEvent event = eagerFetchSportEvent(eventID);
		notificationRepositroy.deleteEventRequest(event, applicant);
		event.setStatus(EventStatus.NOT_MEMBER);
		return event;
	}

	@Override
	@Transactional
	public SportEvent removeUserFromSportEvent(long eventID) {
		User applicant = userRepository.findByName("Andras");
		SportEvent event = eagerFetchSportEvent(eventID);
		event.setMemberSize(event.getMemberSize() - 1);
		event.getMembers().remove(applicant);
		sportEventRepository.save(event);
		
		event.setStatus(EventStatus.NOT_MEMBER);
		return event;
	}

	@Override
	@Transactional
	public SportEvent closeEvent(long eventID) {
		SportEvent event = eagerFetchSportEvent(eventID);
		event.setIsOpened(false);
		sportEventRepository.save(event);
		event.setStatus(EventStatus.OWNER);
		return event;
	}

	@Override
	public void deleteEvent(long eventID) {
		sportEventRepository.delete(eventID);
	}
	
	private SportEvent eagerFetchSportEvent(long id) {
		SportEvent event = sportEventRepository.findOne(id);
		event.getMembers().size();
		event.getComments().size();
		return event;
	}

}
