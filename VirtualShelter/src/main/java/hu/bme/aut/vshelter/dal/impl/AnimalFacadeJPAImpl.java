package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.dal.AnimalFacade;
import hu.bme.aut.vshelter.entity.Animal;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.ValidationException;
import java.util.List;

public class AnimalFacadeJPAImpl implements AnimalFacade {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void create(Animal animal) throws VirtualShelterException {
        try {
            em.persist(animal);
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
    public List<Animal> findAll() throws VirtualShelterException {
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a",
                    Animal.class);

            return query.getResultList();
        } catch (IllegalArgumentException e) {
            throw new VirtualShelterException(e);
        }
    }

    @Override
    @Transactional
    public Animal findById(long animalId) throws VirtualShelterException {
        try {
            return em.find(Animal.class, animalId);
        } catch (IllegalArgumentException e) {
            throw new VirtualShelterException(e);
        }
    }

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

    @Override
    @Transactional
    public void deleteById(long animalId) throws VirtualShelterException {
        try {
            Query deleteQuery = em.createQuery(
                    "DELETE FROM Advertisement where id=:p")
                    .setParameter("p", animalId);
            deleteQuery.executeUpdate();
        } catch (IllegalArgumentException e) {
            throw new VirtualShelterException(e);
        }
    }
}