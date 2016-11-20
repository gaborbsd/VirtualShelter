package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.entity.User;

@Repository
public interface SportEventDAO extends AbstractRepository<SportEvent>, SportEventDAOCustom{

	List<SportEvent> findByOwner(User u);
	
	List<SportEvent> findByIsPublic(boolean isPublic, Sort sort);
	
	List<SportEvent> findByIsOpened(boolean isOpened);
}
