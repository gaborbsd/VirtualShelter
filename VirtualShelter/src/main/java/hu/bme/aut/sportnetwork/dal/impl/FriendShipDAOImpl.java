package hu.bme.aut.sportnetwork.dal.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import hu.bme.aut.sportnetwork.dal.FriendShipDAOCustom;
import hu.bme.aut.sportnetwork.entity.Conversation;
import hu.bme.aut.sportnetwork.entity.Conversation_;
import hu.bme.aut.sportnetwork.entity.FriendShip;
import hu.bme.aut.sportnetwork.entity.FriendShip_;
import hu.bme.aut.sportnetwork.entity.User;

public class FriendShipDAOImpl implements FriendShipDAOCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<FriendShip> findByUser(User user) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<FriendShip> cq = cb.createQuery(FriendShip.class);
		Root<FriendShip> f = cq.from(FriendShip.class);
		cq.select(f);
		Predicate user1 = cb.equal(f.get(FriendShip_.user1), user);
		Predicate user2 = cb.equal(f.get(FriendShip_.user2), user);
		cq.where(cb.or(user1, user2));

		TypedQuery<FriendShip> query = em.createQuery(cq);

		return query.getResultList();
	}

	@Override
	public List<FriendShip> findByListeningUser(User user) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<FriendShip> cq = cb.createQuery(FriendShip.class);
		Root<FriendShip> f = cq.from(FriendShip.class);
		cq.select(f);
		Predicate user1 = cb.equal(f.get(FriendShip_.user1), user);
		Predicate user2Listen = cb.equal(f.get(FriendShip_.user2Listen), true);
		Predicate u1Valid = cb.and(user1, user2Listen);

		Predicate user2 = cb.equal(f.get(FriendShip_.user2), user);
		Predicate user1Listen = cb.equal(f.get(FriendShip_.user1Listen), true);
		Predicate u2Valid = cb.and(user2, user1Listen);

		cq.where(cb.or(u1Valid, u2Valid));
		
		TypedQuery<FriendShip> query = em.createQuery(cq);

		return query.getResultList();
	}

}
