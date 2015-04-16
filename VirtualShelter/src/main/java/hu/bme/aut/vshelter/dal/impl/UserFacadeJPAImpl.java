package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.dal.UserFacade;
import hu.bme.aut.vshelter.entity.User;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.persistence.TypedQuery;

public class UserFacadeJPAImpl implements UserFacade {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public User findUserById(long userId) {
		return em.find(User.class, userId);
	}

	@Override
	@Transactional
	public List<User> findAll() {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u",
				User.class);

		return query.getResultList();
	}

	@Override
	@Transactional
	public void create(User user) {
		em.persist(user);
	}

	@Override
	@Transactional
	public void edit(User user) {
		em.merge(user);
	}

	@Override
	@Transactional
	public void deleteUserById(long userId) {
		Query deleteQuery = em.createQuery(
				"DELETE FROM User where id=:p")
				.setParameter("p", userId);
		deleteQuery.executeUpdate();
	}

	@Override
	@Transactional
	public void promoteUserToSiteAdministrator(long userId) {
		
		String role = "site-administrator";
		User user = this.findUserById(userId);
		
		if( !user.getRoles().contains(role)) {
			user.getRoles().add(role);
		}
	}
	
	@Override
	@Transactional
	public void revokeUserFromSiteAdministrator(long userId) {
		
		String role = "site-administrator";
		User user = this.findUserById(userId);
		
		if( user.getRoles().contains(role)) {
			user.getRoles().remove(role);
		}
	}

	@Override
	@Transactional
	public long getUserIdfromEmail(String email) throws VirtualShelterException {
		List<Integer> list = em.createQuery(
		        "SELECT u.id FROM User u WHERE u.email = ?1").setParameter(1, email).getResultList();
		return list.get(0);
	}

}
