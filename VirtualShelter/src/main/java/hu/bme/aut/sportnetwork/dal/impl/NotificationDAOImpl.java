package hu.bme.aut.sportnetwork.dal.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import hu.bme.aut.sportnetwork.dal.NotificationDAOCustom;
import hu.bme.aut.sportnetwork.entity.FriendNotification;
import hu.bme.aut.sportnetwork.entity.User;

public class NotificationDAOImpl implements NotificationDAOCustom {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<FriendNotification> getFriendRequestSenders(User user) {
		TypedQuery<FriendNotification> query = em.createQuery("SELECT f FROM FriendNotification f WHERE f.owner = :user"
				+ " AND status = hu.bme.aut.sportnetwork.entity.NotificationStatus.SENT", FriendNotification.class);
		query.setParameter("user", user);
		return query.getResultList();
	}

}
