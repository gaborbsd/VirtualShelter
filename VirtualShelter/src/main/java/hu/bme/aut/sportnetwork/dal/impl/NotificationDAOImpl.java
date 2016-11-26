package hu.bme.aut.sportnetwork.dal.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

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

	@Override
	@Transactional
	public void deleteEventRequest(SportEvent e, User u) {
		Query query = em.createQuery("DELETE FROM EventRequestNotification WHERE event = :event AND sender = :sender");
		query.setParameter("event", e);
		query.setParameter("sender", u);
		
		query.executeUpdate();
		
	}

	@Override
	@Transactional
	public void deleteNotificationsOfEvent(long eventId) {
		Query query1 = em.createQuery("DELETE FROM EventRequestNotification WHERE event.id = :id");
		query1.setParameter("id", eventId);

		query1.executeUpdate();

		Query query2 = em.createQuery("DELETE FROM EventSimpleNotification WHERE event.id = :id");
		query2.setParameter("id", eventId);

		query2.executeUpdate();
	}

	@Override
	public FriendRequestNotification isFriendRequestBetween(User u1, User u2) {
		TypedQuery<FriendRequestNotification> query = em.createQuery(
				"SELECT f from FriendRequestNotification f WHERE (sender = :u1 AND owner = :u2) OR (sender = :u2 AND owner = :u1)",
				FriendRequestNotification.class);
		query.setParameter("u1", u1);
		query.setParameter("u2", u2);
		
		FriendRequestNotification ret = null;

		try {
			ret = query.getSingleResult();
		} catch (NoResultException e) {

		}

		return ret;
	}

}
