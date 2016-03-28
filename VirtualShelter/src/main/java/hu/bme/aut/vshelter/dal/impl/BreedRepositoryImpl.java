package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.dal.BreedRepositoryCustom;
import hu.bme.aut.vshelter.entity.Breed;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TransactionRequiredException;
import javax.transaction.Transactional;
import javax.validation.ValidationException;

public class BreedRepositoryImpl implements BreedRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void edit(Breed breed) throws VirtualShelterException {
        try {
            em.merge(breed);
        } catch (IllegalArgumentException e) {
            throw new VirtualShelterException(e);
        } catch (TransactionRequiredException e) {
            throw new VirtualShelterException(e);
        } catch (ValidationException e) {
            throw new VirtualShelterException(e);
        }
    }
}
