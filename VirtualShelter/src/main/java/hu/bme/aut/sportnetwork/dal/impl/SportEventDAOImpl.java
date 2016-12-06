package hu.bme.aut.sportnetwork.dal.impl;

import java.util.ArrayList;
import java.util.List;

import hu.aut.bme.sportnetwork.api.impl.SportEventFilter;
import hu.bme.aut.sportnetwork.dal.SportEventDAO;
import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.entity.User;

public class SportEventDAOImpl implements SportEventDAO {

	@Override
	public List<SportEvent> findByOwner(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SportEvent> findByIsPublic(boolean isPublic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SportEvent> findByIsOpened(boolean isOpened) {
		return new ArrayList<SportEvent>();
	}

	@Override
	public List<SportEvent> findByOwnerInAndIsOpened(List<User> users, boolean isOpened) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SportEvent> findByMembersAndIsOpened(User user, boolean isOpened) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SportEvent> findByTitleLike(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SportEvent saveNewEvent(SportEvent e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SportEvent> filterPublic(SportEventFilter arg) {
		// TODO Auto-generated method stub
		return null;
	}

}
