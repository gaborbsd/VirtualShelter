package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.dal.AnimalFacade;
import hu.bme.aut.vshelter.dal.AnimalRepositoryCustom;
import hu.bme.aut.vshelter.entity.Advertisement;
import hu.bme.aut.vshelter.entity.Animal;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;
import javax.validation.ValidationException;

import org.springframework.transaction.annotation.Transactional;

public class AnimalRepositoryImpl implements AnimalRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void edit(Animal animal) throws VirtualShelterException {
		try {
			em.merge(animal);
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		} catch (TransactionRequiredException e) {
			throw new VirtualShelterException(e);
		} catch (ValidationException e) {
			throw new VirtualShelterException(e);
		} 
	}
}