package hu.bme.aut.sportnetwork.api;

import java.util.List;

import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.entity.User;
import hu.bme.aut.sportnetwork.rest.resources.FilterSportEventArg;
import hu.bme.aut.sportnetwork.rest.resources.RateUsersArg;

public interface SportEventOperations {
	
	List<SportEvent> findAllOpenedEvents();
	
	List<SportEvent> filterPublicEvents(FilterSportEventArg arg) throws Exception;
	
	SportEvent findById(long id);
	
	SportEvent create(SportEvent e);
	
	SportEvent removeUserFromSportEvent(long eventID);
	
	List<SportEvent> findEventsByOwner(User owner);
	
	void acceptEventRequest(long notificationId) throws Exception;
	
	List<SportEvent> listPublicOpenedEvents();
	
	SportEvent applyToSportEvent(long eventID) throws Exception;
	
	SportEvent cancelEventRequest(long eventID);
	
	void rateUser(long eventID, long userId, int value);
	
	SportEvent writeComment(long eventID, String comment);
	
	SportEvent deleteComment(long commentID);
	
	SportEvent closeEvent(long eventID) throws Exception;
	
	void deleteEvent(long eventID);

	List<User> getMembersOfSportEvent(long notificationId);
	
	void rateUsers(RateUsersArg arg) throws Exception;

	List<SportEvent> findFriendEvents();

	List<SportEvent> findMyClosedEvents();

	List<SportEvent> findMyEvents();
}
