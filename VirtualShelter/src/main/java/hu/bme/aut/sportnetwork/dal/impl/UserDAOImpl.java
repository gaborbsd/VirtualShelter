package hu.bme.aut.sportnetwork.dal.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import hu.bme.aut.sportnetwork.dal.UserDAOCustom;
import hu.bme.aut.sportnetwork.entity.Address;
import hu.bme.aut.sportnetwork.entity.FriendShip;
import hu.bme.aut.sportnetwork.entity.FriendStatus;
import hu.bme.aut.sportnetwork.entity.Rating;
import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.entity.User;
import hu.bme.aut.sportnetwork.rest.resources.RateUsersArg;
import hu.bme.aut.sportnetwork.rest.resources.RateWrapper;
import hu.bme.aut.sportnetwork.rest.resources.UserArg;

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

	@Override
	@Transactional
	public void rateUsers(RateParam param, SportEvent event) {
		TypedQuery<Rating> query = em
				.createQuery("SELECT r FROM Rating r WHERE r.user = :user AND r.sport = :sport", Rating.class);

		for (int i = 0; i < param.getRates().size(); i++) {
			query.setParameter("user", param.getUsers().get(i));
			query.setParameter("sport", event.getType());

			Rating r = null;

			try {

				r = query.getSingleResult();
				r.setSumValue(r.getSumValue() + param.getRates().get(i));
				r.setRateNumbers(r.getRateNumbers() + 1);

				em.merge(r);
			} catch (NoResultException e) {

				r = new Rating();
				r.setRateNumbers(1);
				r.setSumValue(param.getRates().get(i));
				r.setSport(event.getType());
				r.setUser(param.getUsers().get(i));

				em.persist(r);
			}

		}

	}

	@Override
	@Transactional
	public User saveNewUser(User u) {
		TypedQuery<Address> addressQuery = em
				.createQuery("SELECT a FROM Address a WHERE a.country=:country AND a.city=:city AND a.address=:address",
						Address.class);
		addressQuery.setParameter("country", u.getAddress().getCountry());
		addressQuery.setParameter("city", u.getAddress().getCity());
		addressQuery.setParameter("address", u.getAddress().getAddress());

		Address a = null;
		try {
			a = addressQuery.getSingleResult();
		} catch (NoResultException ex) {

		}

		User ret = null;

		if (a != null) {
			u.setAddress(a);
			ret = em.merge(u);
		} else {
			em.persist(u);
			ret = u;
		}

		return ret;

	}

	@Override
	public User modifyUser(User modified) {
		TypedQuery<Address> addressQuery = em.createQuery(
				"SELECT a FROM Address a WHERE a.country=:country AND a.city=:city AND a.address=:address",
				Address.class);
		addressQuery.setParameter("country", modified.getAddress().getCountry());
		addressQuery.setParameter("city", modified.getAddress().getCity());
		addressQuery.setParameter("address", modified.getAddress().getAddress());

		Address a = null;
		try {
			a = addressQuery.getSingleResult();
		} catch (NoResultException ex) {

		}

		if (a != null) {
			modified.setAddress(a);
		}
		User ret = em.merge(modified);

		return ret;
	}

}
