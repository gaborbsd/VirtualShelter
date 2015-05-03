package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.dal.UserFacade;
import hu.bme.aut.vshelter.entity.User;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.transaction.Transactional;
import javax.persistence.TypedQuery;

public class UserFacadeJPAImpl implements UserFacade {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public User findById(long userId) throws VirtualShelterException {
		try {
			return em.find(User.class, userId);
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		}
	}

	@Override
	@Transactional
	public List<User> findAll() throws VirtualShelterException {
		try {
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u",
					User.class);
	
			return query.getResultList();
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		}
	}

	@Override
	@Transactional
	public void create(User user) throws VirtualShelterException {
		try {
			em.persist(user);
		} catch (EntityExistsException e) {
			throw new VirtualShelterException(e);
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		} catch (TransactionRequiredException e) {
			throw new VirtualShelterException(e);
		}
	}

	@Override
	@Transactional
	public void edit(User user) throws VirtualShelterException {
		try {
			em.merge(user);
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		} catch (TransactionRequiredException e) {
			throw new VirtualShelterException(e);
		}
	}

	@Override
	@Transactional
	public void deleteById(long userId) throws VirtualShelterException {
		try {
			Query deleteQuery = em.createQuery("DELETE FROM User where id=:p")
					.setParameter("p", userId);
			deleteQuery.executeUpdate();
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		}
	}

	@Override
	@Transactional
	public void promoteUserToSiteAdministrator(long userId) throws VirtualShelterException {

		String role = "site-administrator";
		User user = this.findById(userId);

		if (!user.getRoles().contains(role)) {
			user.getRoles().add(role);
		}
	}

	@Override
	@Transactional
	public void revokeUserFromSiteAdministrator(long userId) throws VirtualShelterException {

		String role = "site-administrator";
		User user = this.findById(userId);

		if (user.getRoles().contains(role)) {
			user.getRoles().remove(role);
		}
	}

	@Override
	@Transactional
	public long getUserIdfromEmail(String email) throws VirtualShelterException {
		TypedQuery<Long> query = em.createQuery(
				"SELECT u.id FROM User u WHERE u.email = ?1", Long.class)
				.setParameter(1, email);
		return query.getSingleResult();
	}

}
