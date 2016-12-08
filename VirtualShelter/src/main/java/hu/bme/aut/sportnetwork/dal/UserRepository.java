package hu.bme.aut.sportnetwork.dal;



import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.bme.aut.sportnetwork.entity.User;

@Repository
public interface UserRepository extends GraphRepository<User> {

	User findByName(String name);

	User findByEmail(String email);

	@Query("MATCH (n:User {name: {name}}) SET n.hasNotification={hasNotifications}")
	void updateNotificationsByName(@Param("name") String name, @Param("hasNotifications") boolean hasNotifications);

	@Query("MATCH (n:User {name: {name}}) RETURN n.hasNotification")
	boolean getHasNotificationByName(@Param("name") String name);
}
