package hu.bme.aut.sportnetwork.dal.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import hu.bme.aut.sportnetwork.dal.UserDAOCustom;
import hu.bme.aut.sportnetwork.entity.FriendShip;
import hu.bme.aut.sportnetwork.entity.FriendStatus;
import hu.bme.aut.sportnetwork.entity.User;

public class UserDAOImpl implements UserDAOCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public List<User> getFriendsOfUser(String name) {
		/*
		 * TypedQuery<FriendShip> query = em.
		 * createQuery("SELECT f FROM User u JOIN u.friends f WHERE name = :name"
		 * , FriendShip.class); query.setParameter("name", name); List<User> ret
		 * = new ArrayList<>(); List<FriendShip> res = query.getResultList();
		 * res.forEach(f -> ret.add(f.getUser1())); ret.forEach(u ->
		 * u.setFriendStatus(FriendStatus.FRIEND)); return ret;
		 */
		return new ArrayList<>();
	}

}
