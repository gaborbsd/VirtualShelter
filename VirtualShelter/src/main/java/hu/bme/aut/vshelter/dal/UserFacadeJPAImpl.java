package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.entity.Role;
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
	public User findUserById(long userId) {
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
	public void deleteUserById(long userId) {
		Query deleteQuery = em.createQuery(
				"DELETE FROM User where id=:p")
				.setParameter("p", userId);
		deleteQuery.executeUpdate();
	}

	@Override
	public void promoteUserToSiteAdministrator(int userId) {
		List<Role> list = em.createQuery(
		        "SELECT r FROM Role r WHERE r.roleName = 'site-administrator'").getResultList();
		Role role = list.get(0);
		User user = this.findUserById(userId);
		
		if( !user.getRoles().contains(role)) {
			user.getRoles().add(role);
		}
	}
	
	@Override
	public void revokeUserFromSiteAdministrator(int userId) {
		List<Role> list = em.createQuery(
		        "SELECT r FROM Role r WHERE r.roleName = 'site-administrator'").getResultList();
		Role role = list.get(0);
		User user = this.findUserById(userId);
		
		if( user.getRoles().contains(role)) {
			user.getRoles().remove(role);
		}
	}

	@Override
	public int getUserIdfromEmail(String email) throws VirtualShelterException {
		List<Integer> list = em.createQuery(
		        "SELECT u.id FROM User u WHERE u.email = ?1").setParameter(1, email).getResultList();
		return list.get(0);
	}

}
