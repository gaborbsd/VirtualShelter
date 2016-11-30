package hu.bme.aut.sportnetwork.dal.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import hu.bme.aut.sportnetwork.dal.ConversationDAOCustom;
import hu.bme.aut.sportnetwork.entity.Conversation;
import hu.bme.aut.sportnetwork.entity.Conversation_;
import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.entity.User;

public class ConversationDAOImpl implements ConversationDAOCustom {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Conversation> findByUserAndActive(User user) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Conversation> cq = cb.createQuery(Conversation.class);
		Root<Conversation> c = cq.from(Conversation.class);
		cq.select(c);
		Predicate user1 = cb.equal(c.get(Conversation_.user1), user);
		Predicate user2 = cb.equal(c.get(Conversation_.user2), user);
		Predicate userPred = cb.or(user1, user2);
		Predicate activ = cb.equal(c.get(Conversation_.active), true);

		cq.where(cb.and(userPred, activ));
		cq.orderBy(cb.desc(c.get(Conversation_.lastSendTime)));

		TypedQuery<Conversation> query = em.createQuery(cq);

		return query.getResultList();
	}


}
