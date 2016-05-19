package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.entity.User;

public interface SportEventRepository extends AbstractRepository<SportEvent> {

	List<SportEvent> findByOwner(User u);
}
