package hu.bme.aut.sportnetwork.dal.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import hu.bme.aut.sportnetwork.dal.NotificationDAOCustom;
import hu.bme.aut.sportnetwork.entity.FriendRequestNotification;
import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.entity.User;

public class NotificationDAOImpl implements NotificationDAOCustom {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<FriendRequestNotification> getFriendRequestSenders(User user) {
		TypedQuery<FriendRequestNotification> query = em.createQuery("SELECT f FROM FriendNotification f WHERE f.owner = :user"
				+ " AND status = hu.bme.aut.sportnetwork.entity.NotificationStatus.SENT", FriendRequestNotification.class);
		query.setParameter("user", user);
		return query.getResultList();
	}

	@Override
	public boolean isUserAppliedToEvent(SportEvent e, User u) {
		Query query = em.createQuery("SELECT count(n) FROM EventRequestNotification n WHERE event = :event AND sender = :sender");
		query.setParameter("event", e);
		query.setParameter("sender", u);
		
		int res = ((Number) query.getSingleResult()).intValue();
		
		return res != 0;
	}

}
