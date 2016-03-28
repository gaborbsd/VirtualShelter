package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.dal.HandicapFacade;
import hu.bme.aut.vshelter.entity.Handicap;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.List;

public class HandicapFacadeJPAImpl implements HandicapFacade {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Handicap findById(long handicapId) throws VirtualShelterException {
        try {
            return em.find(Handicap.class, handicapId);
        } catch (IllegalArgumentException e) {
            throw new VirtualShelterException(e);
        }
    }

    @Override
    @Transactional
    public List<Handicap> findAll() throws VirtualShelterException {
        try {
            TypedQuery<Handicap> query = em.createQuery("SELECT h FROM Handicap h",
                    Handicap.class);

            return query.getResultList();
        } catch (IllegalArgumentException e) {
            throw new VirtualShelterException(e);
        }
    }

    @Override
    @Transactional
    public void create(Handicap handicap) throws VirtualShelterException {
        try {
            em.persist(handicap);
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
    public void edit(Handicap handicap) throws VirtualShelterException {
        try {
            em.merge(handicap);
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
    public void deleteById(long handicapId) throws VirtualShelterException {
        try {
            Query deleteQuery = em.createQuery(
                    "DELETE FROM Handicap where id=:p")
                    .setParameter("p", handicapId);
            deleteQuery.executeUpdate();
        } catch (IllegalArgumentException e) {
            throw new VirtualShelterException(e);
        }
    }

}
