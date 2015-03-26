package hu.bme.aut.vshelter.dal;

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
	public User findUserById(int userId) {
		return em.find(User.class, userId);
	}

	@Override
	public List<User> findAll() {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u",
				User.class);

		return query.getResultList();
	}

	@Override
	public void create(User user) {
		em.persist(user);
	}

	@Override
	public void edit(User user) {
		em.merge(user);
	}

	@Override
	public void deleteUserById(int userId) {
		Query deleteQuery = em.createQuery(
				"DELETE FROM User where id=:p")
				.setParameter("p", userId);
		deleteQuery.executeUpdate();
	}

}
