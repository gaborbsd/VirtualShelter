package hu.aut.bme.sportnetwork.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import hu.bme.aut.sportnetwork.api.ISportEventOperations;
import hu.bme.aut.sportnetwork.dal.SportEventRepository;
import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.entity.User;

public class SportEventOperations implements ISportEventOperations {
	
	@Autowired
	SportEventRepository sportEventRepository;

	@Override
	public List<SportEvent> findAll() {
		return sportEventRepository.findAll();
	}

	@Override
	public SportEvent create(SportEvent e) {
		return sportEventRepository.save(e);
	}

	@Override
	public List<SportEvent> findEventsByOwner(User owner) {
		return sportEventRepository.findByOwner(owner);
	}

	@Override
	public void addUserToEvent(SportEvent e, User u) {
		
	}

}
