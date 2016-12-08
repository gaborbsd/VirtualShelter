package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.neo4j.repository.GraphRepository;

import hu.bme.aut.sportnetwork.entity.Notification;

public interface NotificationRepository extends GraphRepository<Notification> {

	List<Notification> findByOwnerName(String name, Sort sort);

}
