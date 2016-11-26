package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import hu.bme.aut.sportnetwork.dal.impl.RateParam;
import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.entity.User;
import hu.bme.aut.sportnetwork.rest.resources.RateUsersArg;
import hu.bme.aut.sportnetwork.rest.resources.UserArg;

public interface UserDAOCustom {
	
	List<User> getFriendsOfUser(String name);
	
	void rateUsers(RateParam arg, SportEvent event);

	User saveNewUser(User user);

}
