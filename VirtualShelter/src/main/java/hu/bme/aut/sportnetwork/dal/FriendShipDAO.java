package hu.bme.aut.sportnetwork.dal;

import org.springframework.data.jpa.repository.Query;

import hu.bme.aut.sportnetwork.entity.FriendShip;
import hu.bme.aut.sportnetwork.entity.User;

public interface FriendShipDAO extends AbstractRepository<FriendShip>, FriendShipDAOCustom {

	@Query("SELECT f FROM FriendShip f WHERE (user1 = ?1 AND user2 = ?2) OR (user2 = ?1 AND user1 = ?2)")
	FriendShip getByUser1AndUser2(User user1, User user2);
}
