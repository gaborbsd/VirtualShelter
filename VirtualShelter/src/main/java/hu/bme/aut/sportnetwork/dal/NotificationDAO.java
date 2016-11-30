package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import hu.bme.aut.sportnetwork.entity.EventRequestNotification;
import hu.bme.aut.sportnetwork.entity.Notification;
import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.entity.User;

@Repository
public interface NotificationDAO extends AbstractRepository<Notification>, NotificationDAOCustom{
	
	List<Notification> findByOwnerAndIsDeclined(User owner, Boolean isDeclined, Sort sort);


}
