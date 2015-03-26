package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.User;

import java.util.List;

public interface UserFacade {
	
	User findUserById(int userId);
	
	List<User> findAll();
	
	void create(User user);
	
	void edit(User user);
	
	void deleteUserById(int userId);

}
