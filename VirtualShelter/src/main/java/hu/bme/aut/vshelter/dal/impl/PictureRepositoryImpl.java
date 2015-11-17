package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.dal.PictureFacade;
import hu.bme.aut.vshelter.dal.PictureRepositoryCustom;
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

public class PictureRepositoryImpl implements PictureRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;

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
}
