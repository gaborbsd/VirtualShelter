package hu.bme.aut.sportnetwork.api;

import java.util.List;

import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.entity.User;
import hu.bme.aut.sportnetwork.rest.resources.FilterSportEventArg;

public interface SportEventOperations {
	
	List<SportEvent> findAll();
	
	List<SportEvent> filterPublicEvents(FilterSportEventArg arg) throws Exception;
	
	SportEvent findById(long id);
	
	SportEvent create(SportEvent e);
	
	List<SportEvent> findEventsByOwner(User owner);
	
	void acceptEventRequest(long notificationId) throws Exception;
	
	List<SportEvent> listPublicEvents();
	
	void applyToSportEvent(long eventID) throws Exception;
	
	void rateUser(long eventID, long userId, int value);
	
	void writeComment(long eventID, String comment);
	
	void deleteComment(long commentID);
}
