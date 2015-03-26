package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Species;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.persistence.TypedQuery;

public class SpeciesFacadeImpl implements SpeciesFacade {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public Species findSpeciesById(int speciesId) {
		return em.find(Species.class, speciesId);
	}

	@Override
	@Transactional
	public List<Species> findAll() {
		TypedQuery<Species> query = em.createQuery("SELECT s FROM Species s",
				Species.class);

		return query.getResultList();
	}

	@Override
	@Transactional
	public void create(Species species) {
		em.persist(species);
	}

	@Override
	@Transactional
	public void edit(Species species) {
		em.merge(species);
	}

	@Override
	@Transactional
	public void deleteSpeciesById(int speciesId) {
		Query deleteQuery = em.createQuery(
				"DELETE FROM Species where id=:p")
				.setParameter("p", speciesId);
		deleteQuery.executeUpdate();
	}

}
