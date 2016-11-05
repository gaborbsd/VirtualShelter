package hu.bme.aut.sportnetwork.api;

import java.util.List;

import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.entity.User;

public interface ISportEventOperations {
	
	List<SportEvent> findAll();
	
	SportEvent create(SportEvent e);
	
	List<SportEvent> findEventsByOwner(User owner);
	
	void addUserToEvent(SportEvent e, User u);
	
	void sendEventNotifications(List<String> names, String message);

}
