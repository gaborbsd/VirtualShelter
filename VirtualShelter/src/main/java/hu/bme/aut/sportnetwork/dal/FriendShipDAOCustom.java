package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import hu.bme.aut.sportnetwork.entity.FriendShip;
import hu.bme.aut.sportnetwork.entity.User;

public interface FriendShipDAOCustom {

	List<FriendShip> findByUser(User user);

	List<FriendShip> findByListeningUser(User user);

}
