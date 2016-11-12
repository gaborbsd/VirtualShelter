package hu.bme.aut.sportnetwork.dal.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import hu.bme.aut.sportnetwork.dal.UserRepositoryCustom;
import hu.bme.aut.sportnetwork.entity.User;

public class UserDAO implements UserRepositoryCustom {
	
	/*@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public User getUserByName(String name) {
		TypedQuery<User> query = em.createQuery(
				"SELECT u FROM User u WHERE u.name = ?1", User.class)
				.setParameter(1, name);
		return query.getSingleResult();
	}*/


}
