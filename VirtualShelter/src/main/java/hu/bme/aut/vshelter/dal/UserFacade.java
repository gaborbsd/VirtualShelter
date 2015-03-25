package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.User;

import java.util.List;

public interface UserFacade {
	
	User findUserById(int userId);
	
	List<User> findAll();
	
	void create(User User);
	
	void edit(User User);
	
	void deleteUserById(int userId);

}
