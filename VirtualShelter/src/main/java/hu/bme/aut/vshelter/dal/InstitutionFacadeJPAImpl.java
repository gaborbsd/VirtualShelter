package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Institution;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.persistence.TypedQuery;

public class InstitutionFacadeJPAImpl implements InstitutionFacade {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public Institution findInstitutionById(int institutionId) {
		return em.find(Institution.class, institutionId);
	}

	@Override
	@Transactional
	public List<Institution> findAll() {
		TypedQuery<Institution> query = em.createQuery("SELECT i FROM Institution i",
				Institution.class);

		return query.getResultList();
	}

	@Override
	@Transactional
	public void create(Institution institution) {
		em.persist(institution);

	}

	@Override
	@Transactional
	public void edit(Institution institution) {
		em.merge(institution);
	}

	@Override
	@Transactional
	public void deleteInstitutionById(int institutionId) {
		Query deleteQuery = em.createQuery(
				"DELETE FROM Institution where id=:p")
				.setParameter("p", institutionId);
		deleteQuery.executeUpdate();
	}

}
