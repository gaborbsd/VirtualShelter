package hu.aut.bme.sportnetwork.api.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import hu.bme.aut.sportnetwork.api.SportEventOperations;
import hu.bme.aut.sportnetwork.dal.FriendShipDAO;
import hu.bme.aut.sportnetwork.dal.NotificationDAO;
import hu.bme.aut.sportnetwork.dal.SportEventDAO;
import hu.bme.aut.sportnetwork.dal.UserDAO;
import hu.bme.aut.sportnetwork.entity.EventNotification;
import hu.bme.aut.sportnetwork.entity.FriendNotification;
import hu.bme.aut.sportnetwork.entity.FriendShip;
import hu.bme.aut.sportnetwork.entity.Notification;
import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.entity.User;

public class SportEventOperationsImpl implements SportEventOperations {
	
	@Autowired
	SportEventDAO sportEventRepository;
	
	@Autowired
	UserDAO userRepository;
	
	@Autowired
	FriendShipDAO friendShipRepository;
	
	@Autowired
	NotificationDAO notificationRepositroy;

	@Override
	public List<SportEvent> findAll() {
		return sportEventRepository.findAll();
	}

	@Override
	public SportEvent create(SportEvent e) {
		//User owner = e.getOwner();
		User owner = userRepository.findByName("Andras");
		SportEvent newEvent = sportEventRepository.save(e);
		newEvent.setOwner(owner);
		List<Object> usersToNotify = friendShipRepository.findByPersonAndListenNotifications(owner, true);
		usersToNotify.forEach(u -> sendEventNotification((User)u, owner, newEvent));
		return newEvent;
	}

	@Override
	public List<SportEvent> findEventsByOwner(User owner) {
		return sportEventRepository.findByOwner(owner);
	}

	private void sendEventNotification(User sendTo, User sender, SportEvent e) {
		Notification not = new EventNotification(sender, e);
		not.setOwner(sendTo);
		not.setSendTime(new Date());
		not.setMessage("NEW EVENT CREATED");
		notificationRepositroy.save(not);
	}

	@Override
	public List<SportEvent> listPublicEvents() {
		return sportEventRepository.findByIsPublic(true, new Sort("date"));
	}

	@Override
	public void applyToSportEvent(long eventID) throws Exception {
		User applicant = userRepository.findByName("Elemer");
		SportEvent event = sportEventRepository.findOne(eventID);
		if (event.getMaxSize() <= event.getMembers().size()) {
			throw new Exception("NO SPACE");
		}
		sendEventNotification(event.getOwner(), applicant, event);		
	}

	@Override
	@Transactional
	public void acceptEventRequest(long notificationId) throws Exception{		
		Notification not = notificationRepositroy.findOne(notificationId);
		//User accepter = not.getOwner();
		if (not instanceof EventNotification) {
			EventNotification eventNot = (EventNotification) not;
			SportEvent event = eventNot.getEvent();
			User applicant = eventNot.getSender();
			if (event.getMaxSize() <= event.getMembers().size()) {
				throw new Exception("NO SPACE");
			}
			event.getMembers().add(applicant);
			sportEventRepository.save(event);
			notificationRepositroy.delete(notificationId);
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
	public SportEvent findById(long id) {
		return sportEventRepository.findOne(id);
	}

}
