package hu.bme.aut.sportnetwork.api;

import hu.bme.aut.sportnetwork.entity.User;

public interface IUserOperation {
	
	User findById(long id);
}
