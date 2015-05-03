package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.dal.PictureFacade;
import hu.bme.aut.vshelter.entity.Picture;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.persistence.TypedQuery;

public class PictureFacadeJPAImpl implements PictureFacade {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public Picture findById(long pictureId) {
		return em.find(Picture.class, pictureId);
	}

	@Override
	@Transactional
	public List<Picture> findAll() {
		TypedQuery<Picture> query = em.createQuery("SELECT p FROM Picture p",
				Picture.class);

		return query.getResultList();
	}

	@Override
	@Transactional
	public void create(Picture picture) {
		em.persist(picture);
	}

	@Override
	@Transactional
	public void edit(Picture picture) {
		em.merge(picture);
	}

	@Override
	@Transactional
	public void deleteById(long pictureId) {
		Query deleteQuery = em.createQuery(
				"DELETE FROM em where id=:p")
				.setParameter("p", pictureId);
		deleteQuery.executeUpdate();
	}

}
