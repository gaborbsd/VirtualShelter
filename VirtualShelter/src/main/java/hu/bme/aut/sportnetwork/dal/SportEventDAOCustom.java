package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import hu.aut.bme.sportnetwork.api.impl.SportEventFilter;
import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.entity.User;

public interface SportEventDAOCustom {
	
	SportEvent saveNewEvent(SportEvent e);
	
	List<SportEvent> filterPublic(SportEventFilter arg) throws Exception;

}
