package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import hu.aut.bme.sportnetwork.api.impl.SportEventFilter;
import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.entity.User;

public interface SportEventDAO {

	List<SportEvent> findByOwner(User u);
	
	List<SportEvent> findByIsPublic(boolean isPublic);
	
	List<SportEvent> findByIsOpened(boolean isOpened);

	List<SportEvent> findByOwnerInAndIsOpened(List<User> users, boolean isOpened);

	List<SportEvent> findByMembersAndIsOpened(User user, boolean isOpened);

	List<SportEvent> findByTitleLike(String title);

	SportEvent saveNewEvent(SportEvent e);

	List<SportEvent> filterPublic(SportEventFilter arg);
}
