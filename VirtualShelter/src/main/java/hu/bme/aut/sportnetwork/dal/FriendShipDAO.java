package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import hu.bme.aut.sportnetwork.entity.Friend;
import hu.bme.aut.sportnetwork.entity.User;

public interface FriendShipDAO {

	Friend getByUser1AndUser2(User user1, User user2);

	List<Friend> findByUser(User user);

	List<Friend> findByListeningUser(User user);

}
