package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import hu.bme.aut.sportnetwork.entity.FriendShip;
import hu.bme.aut.sportnetwork.entity.User;

public interface FriendShipDAO extends AbstractRepository<FriendShip>{

	List<FriendShip> findByUser1AndUser2ListenOrUser2AndUser1Listen(User user1, User user2, boolean user1Listen,
			boolean user2Listen);
}
