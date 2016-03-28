package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.dal.SpeciesRepositoryCustom;
import hu.bme.aut.vshelter.entity.Species;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.ValidationException;

public class SpeciesRepositoryImpl implements SpeciesRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void edit(Species species) throws VirtualShelterException {
        try {
            em.merge(species);
        } catch (IllegalArgumentException e) {
            throw new VirtualShelterException(e);
        } catch (TransactionRequiredException e) {
            throw new VirtualShelterException(e);
        } catch (ValidationException e) {
            throw new VirtualShelterException(e);
        }
    }

    @Override
    public long getSpeciesIdfromSpeciesName(String speciesName)
            throws VirtualShelterException {
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT s.id FROM Species s WHERE s.speciesName = ?1",
                    Long.class).setParameter(1, speciesName);
            return query.getSingleResult();
        } catch (IllegalArgumentException e) {
            throw new VirtualShelterException(e);
        }
    }

}
