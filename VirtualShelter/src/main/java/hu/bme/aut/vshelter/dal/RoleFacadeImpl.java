package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Role;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.persistence.TypedQuery;

public class RoleFacadeImpl implements RoleFacade {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public Role findRoleById(int roleId) {
		return em.find(Role.class, roleId);
	}

	@Override
	@Transactional
	public List<Role> findAll() {
		TypedQuery<Role> query = em.createQuery("SELECT r FROM Role r",
				Role.class);

		return query.getResultList();
	}

	@Override
	@Transactional
	public void create(Role role) {
		em.persist(role);
	}

	@Override
	@Transactional
	public void edit(Role role) {
		em.merge(role);
	}

	@Override
	@Transactional
	public void deleteRoleById(int roleId) {
		Query deleteQuery = em.createQuery(
				"DELETE FROM Role where id=:p")
				.setParameter("p", roleId);
		deleteQuery.executeUpdate();
	}

}
