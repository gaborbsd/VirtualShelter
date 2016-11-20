package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import hu.aut.bme.sportnetwork.api.impl.SportEventFilter;
import hu.bme.aut.sportnetwork.entity.SportEvent;

public interface SportEventDAOCustom {
	
	SportEvent saveNewEvent(SportEvent e);
	
	List<SportEvent> filterPublic(SportEventFilter arg) throws Exception;

}
