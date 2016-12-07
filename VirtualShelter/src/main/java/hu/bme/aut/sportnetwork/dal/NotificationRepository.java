package hu.bme.aut.sportnetwork.dal;

import org.springframework.data.neo4j.repository.GraphRepository;

import hu.bme.aut.sportnetwork.entity.Notification;

public interface NotificationRepository extends GraphRepository<Notification> {

}
