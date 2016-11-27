package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import hu.bme.aut.sportnetwork.entity.FriendShip;
import hu.bme.aut.sportnetwork.entity.User;

public interface FriendShipDAO extends AbstractRepository<FriendShip>{

	List<FriendShip> findByUser1AndUser2ListenOrUser2AndUser1Listen(User user1, User user2, boolean user1Listen,
			boolean user2Listen);

	@Query("SELECT f FROM FriendShip f WHERE (user1 = ?1 AND user2 = ?2) OR (user2 = ?1 AND user1 = ?2)")
	FriendShip getByUser1AndUser2(User user1, User user2);

	List<FriendShip> getByUser1OrUser2(User user1, User user2);
}
