package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.entity.User;

@Repository
public interface SportEventDAO extends AbstractRepository<SportEvent>, SportEventDAOCustom{

	List<SportEvent> findByOwner(User u);
	
	List<SportEvent> findByIsPublic(boolean isPublic, Sort sort);
	
	List<SportEvent> findByIsOpened(boolean isOpened);

	List<SportEvent> findByOwnerInAndIsOpened(List<User> users, boolean isOpened);

	@Query("SELECT e from SportEvent e WHERE ?1 MEMBER OF e.members AND e.isOpened = ?2")
	List<SportEvent> findByMembersAndIsOpened(User user, boolean isOpened);
}
