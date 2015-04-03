package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.dal.HandicapFacade;
import hu.bme.aut.vshelter.entity.Handicap;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.persistence.TypedQuery;

public class HandicapFacadeJPAImpl implements HandicapFacade {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public Handicap findHandicapById(long handicapId) {
		return em.find(Handicap.class, handicapId);
	}

	@Override
	@Transactional
	public List<Handicap> findAll() {
		TypedQuery<Handicap> query = em.createQuery("SELECT h FROM Handicap h",
				Handicap.class);

		return query.getResultList();
	}

	@Override
	@Transactional
	public void create(Handicap handicap) {
		em.persist(handicap);
	}

	@Override
	@Transactional
	public void edit(Handicap handicap) {
		em.merge(handicap);
	}

	@Override
	@Transactional
	public void deleteHandicapById(long handicapId) {
		Query deleteQuery = em.createQuery(
				"DELETE FROM Handicap where id=:p")
				.setParameter("p", handicapId);
		deleteQuery.executeUpdate();
	}

}
