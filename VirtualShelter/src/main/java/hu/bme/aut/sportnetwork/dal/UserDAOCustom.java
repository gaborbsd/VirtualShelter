package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import hu.bme.aut.sportnetwork.dal.impl.RateParam;
import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.entity.User;

public interface UserDAOCustom {
	
	void rateUsers(RateParam arg, SportEvent event);

	User saveNewUser(User user);

	User modifyUser(User modified);

	List<User> search(String text);

}
