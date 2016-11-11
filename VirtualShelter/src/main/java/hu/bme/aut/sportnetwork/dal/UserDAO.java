package hu.bme.aut.sportnetwork.dal;

import org.springframework.stereotype.Repository;

import hu.bme.aut.sportnetwork.entity.User;

@Repository
public interface UserDAO extends AbstractRepository<User> {
	
	User findByName(String name);

}
