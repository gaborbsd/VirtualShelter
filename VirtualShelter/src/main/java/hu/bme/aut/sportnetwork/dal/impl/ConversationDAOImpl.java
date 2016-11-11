package hu.bme.aut.sportnetwork.dal.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import hu.bme.aut.sportnetwork.dal.ConversationDAOCustom;
import hu.bme.aut.sportnetwork.entity.Conversation;
import hu.bme.aut.sportnetwork.entity.User;

public class ConversationDAOImpl implements ConversationDAOCustom {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Conversation own(User user1, User user2) {
		TypedQuery<Conversation> query = em.createQuery("SELECT c FROM Conversation c "
				+ "WHERE (c.user1 = :user1 AND c.user2 = :user2) OR (c.user1 = :user2 AND c.user2 = :user1)", Conversation.class);
		query.setParameter("user1", user1);
		query.setParameter("user2", user2);
		
		Conversation ret = null;
		try {
			ret = query.getSingleResult();
		} catch (NoResultException e) {
			
		}
		return ret;
	}

}
