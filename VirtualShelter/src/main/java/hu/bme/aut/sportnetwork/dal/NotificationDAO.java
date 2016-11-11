package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import hu.bme.aut.sportnetwork.entity.Notification;
import hu.bme.aut.sportnetwork.entity.User;

@Repository
public interface NotificationDAO extends AbstractRepository<Notification>{
	
	List<Notification> findByOwner(User u, Sort sort);

}
