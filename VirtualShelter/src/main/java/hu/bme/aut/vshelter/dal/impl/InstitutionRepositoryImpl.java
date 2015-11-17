package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.dal.InstitutionFacade;
import hu.bme.aut.vshelter.dal.InstitutionRepositoryCustom;
import hu.bme.aut.vshelter.entity.Institution;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import javax.persistence.TypedQuery;

public class InstitutionRepositoryImpl implements InstitutionRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void edit(Institution institution) throws VirtualShelterException {
		try {
			em.merge(institution);
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		} catch (TransactionRequiredException e) {
			throw new VirtualShelterException(e);
		} catch (ValidationException e) {
			throw new VirtualShelterException(e);
		} 
	}

	@Override
	public List<Institution> listInstituitionsOwnedByUser(long userId) throws VirtualShelterException {
		try {
			TypedQuery<Institution> query = em.createQuery("SELECT i FROM Institution i where owner_id=:p",
					Institution.class).setParameter("p", userId);
			return query.getResultList();
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		} 
	}

}
