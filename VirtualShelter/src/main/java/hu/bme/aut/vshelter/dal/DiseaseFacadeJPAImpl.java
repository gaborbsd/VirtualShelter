package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Disease;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.persistence.TypedQuery;

public class DiseaseFacadeJPAImpl implements DiseaseFacade {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public Disease findDiseaseById(int diseaseId) {
		return em.find(Disease.class, diseaseId);
	}

	@Override
	@Transactional
	public List<Disease> findAll() {
		TypedQuery<Disease> query = em.createQuery("SELECT d FROM Disease d",
				Disease.class);

		return query.getResultList();
	}

	@Override
	@Transactional
	public void create(Disease disease) {
		em.persist(disease);
	}

	@Override
	@Transactional
	public void edit(Disease disease) {
		em.merge(disease);
	}

	@Override
	@Transactional
	public void deleteDiseaseById(int diseaseId) {
		Query deleteQuery = em.createQuery(
				"DELETE FROM Disease where id=:p")
				.setParameter("p", diseaseId);
		deleteQuery.executeUpdate();
	}

}
