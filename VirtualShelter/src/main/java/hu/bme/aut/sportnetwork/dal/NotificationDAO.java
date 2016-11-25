package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import hu.bme.aut.sportnetwork.entity.Notification;
import hu.bme.aut.sportnetwork.entity.User;

@Repository
public interface NotificationDAO extends AbstractRepository<Notification>, NotificationDAOCustom{
	
	List<Notification> findByOwnerAndIsDeclined(User u, Boolean isDeclined, Sort sort);

}
