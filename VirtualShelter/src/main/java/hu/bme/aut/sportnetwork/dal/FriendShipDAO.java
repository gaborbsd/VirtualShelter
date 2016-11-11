package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import hu.bme.aut.sportnetwork.entity.FriendShip;
import hu.bme.aut.sportnetwork.entity.User;

public interface FriendShipDAO extends AbstractRepository<FriendShip>{

	@Query("SELECT f.friend FROM FriendShip f WHERE f.person=?1 AND f.listenNotifications=?2")
	List<Object> findByPersonAndListenNotifications(User person, boolean listenNotifications);
}
