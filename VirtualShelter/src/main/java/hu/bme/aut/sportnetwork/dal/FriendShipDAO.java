package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import hu.bme.aut.sportnetwork.entity.FriendShip;
import hu.bme.aut.sportnetwork.entity.User;

public interface FriendShipDAO {

	FriendShip getByUser1AndUser2(User user1, User user2);

	List<FriendShip> findByUser(User user);

	List<FriendShip> findByListeningUser(User user);

}
