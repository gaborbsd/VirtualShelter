package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.dal.SpeciesFacade;
import hu.bme.aut.vshelter.entity.Species;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.persistence.TypedQuery;

public class SpeciesFacadeJPAImpl implements SpeciesFacade {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public Species findSpeciesById(long speciesId) {
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
	public void deleteSpeciesById(long speciesId) {
		Query deleteQuery = em.createQuery("DELETE FROM Species where id=:p")
				.setParameter("p", speciesId);
		deleteQuery.executeUpdate();
	}

	@Override
	public long getSpeciesIdfromSpeciesName(String speciesName)
			throws VirtualShelterException {
		TypedQuery<Long> query = em.createQuery(
				"SELECT s.id FROM Species s WHERE s.speciesName = ?1",
				Long.class).setParameter(1, speciesName);
		return query.getSingleResult();
	}

}
