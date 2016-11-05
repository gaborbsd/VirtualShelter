package hu.aut.bme.sportnetwork.api.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import hu.bme.aut.sportnetwork.api.ISportEventOperations;
import hu.bme.aut.sportnetwork.dal.IFriendShipDAO;
import hu.bme.aut.sportnetwork.dal.INotificationDAO;
import hu.bme.aut.sportnetwork.dal.ISportEventDAO;
import hu.bme.aut.sportnetwork.dal.IUserDAO;
import hu.bme.aut.sportnetwork.entity.EventNotification;
import hu.bme.aut.sportnetwork.entity.Notification;
import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.entity.User;

public class SportEventOperations implements ISportEventOperations {
	
	@Autowired
	ISportEventDAO sportEventRepository;
	
	@Autowired
	IUserDAO us;
	
	@Autowired
	IFriendShipDAO friendShipRepository;
	
	@Autowired
	INotificationDAO notificationRepositroy;

	@Override
	public List<SportEvent> findAll() {
		return sportEventRepository.findAll();
	}

	@Override
	public SportEvent create(SportEvent e) {
		//User owner = e.getOwner();
		User owner = us.findByName("Andras");
		SportEvent newEvent = sportEventRepository.save(e);
		newEvent.setOwner(owner);
		List<Object> usersToNotify = friendShipRepository.findByPersonAndListenNotifications(owner, true);
		usersToNotify.forEach(u -> sendEventNotification((User)u, newEvent));
		return newEvent;
	}

	@Override
	public List<SportEvent> findEventsByOwner(User owner) {
		return sportEventRepository.findByOwner(owner);
	}

	@Override
	public void addUserToEvent(SportEvent e, User u) {
		
	}

	private void sendEventNotification(User u, SportEvent e) {
		Notification not = new EventNotification(e);
		not.setOwner(u);
		not.setSendTime(new Date());
		not.setMessage("NEW EVENT CREATED");
		notificationRepositroy.save(not);
	}

}
