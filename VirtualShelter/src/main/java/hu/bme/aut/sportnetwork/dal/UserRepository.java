package hu.bme.aut.sportnetwork.dal;



import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import hu.bme.aut.sportnetwork.entity.User;

@Repository
public interface UserRepository extends GraphRepository<User> {

	User findByName(String name);

	User findByEmail(String email);
}
