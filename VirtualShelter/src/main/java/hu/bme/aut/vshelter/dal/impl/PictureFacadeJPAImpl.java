package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.dal.PictureFacade;
import hu.bme.aut.vshelter.entity.Picture;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import javax.persistence.TypedQuery;

public class PictureFacadeJPAImpl implements PictureFacade {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public Picture findById(long pictureId) throws VirtualShelterException {
		try {
			return em.find(Picture.class, pictureId);
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		}
	}

	@Override
	@Transactional
	public List<Picture> findAll() throws VirtualShelterException {
		try {
			TypedQuery<Picture> query = em.createQuery("SELECT p FROM Picture p",
					Picture.class);
	
			return query.getResultList();
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		}
	}

	@Override
	@Transactional
	public void create(Picture picture) throws VirtualShelterException {
		try {
			em.persist(picture);
		} catch (EntityExistsException e) {
			throw new VirtualShelterException(e);
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		} catch (TransactionRequiredException e) {
			throw new VirtualShelterException(e);
		} catch (ValidationException e) {
			throw new VirtualShelterException(e);
		} 
	}

	@Override
	@Transactional
	public void edit(Picture picture) throws VirtualShelterException {
		try {
			em.merge(picture);
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		} catch (TransactionRequiredException e) {
			throw new VirtualShelterException(e);
		} catch (ValidationException e) {
			throw new VirtualShelterException(e);
		} 
	}

	@Override
	@Transactional
	public void deleteById(long pictureId) throws VirtualShelterException {
		try {
			Query deleteQuery = em.createQuery(
					"DELETE FROM em where id=:p")
					.setParameter("p", pictureId);
			deleteQuery.executeUpdate();
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		}
	}

}
