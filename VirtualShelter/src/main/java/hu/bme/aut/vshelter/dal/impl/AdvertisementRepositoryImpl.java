package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.dal.AdvertisementRepositoryCustom;
import hu.bme.aut.vshelter.entity.Advertisement;
import hu.bme.aut.vshelter.entity.Advertiser;
import hu.bme.aut.vshelter.entity.Animal;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.List;

public class AdvertisementRepositoryImpl implements AdvertisementRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void edit(Advertisement advertisement) throws VirtualShelterException {
        try {
            em.merge(advertisement);
        } catch (IllegalArgumentException e) {
            throw new VirtualShelterException(e);
        } catch (TransactionRequiredException e) {
            throw new VirtualShelterException(e);
        } catch (ValidationException e) {
            throw new VirtualShelterException(e);
        }
    }

    @Override
    public List<Advertisement> listAdvertisementsFromAdvertiser(
            long advertiserId) throws VirtualShelterException {
        try {
            TypedQuery<Advertisement> query = em.createQuery("SELECT a FROM Advertisement a where advertiser_id=:p",
                    Advertisement.class).setParameter("p", advertiserId);
            return query.getResultList();
        } catch (IllegalArgumentException e) {
            throw new VirtualShelterException(e);
        }
    }

    @Override
    public Advertiser getAdvertiserOfAnimal(long animalId) throws VirtualShelterException {
        try {
            Query getAdvertiserOfAnimalQuery = em.createQuery(
                    "SELECT a.advertiser FROM Advertisement a WHERE a.animal.id=:p")
                    .setParameter("p", animalId);

            return (Advertiser) getAdvertiserOfAnimalQuery.getSingleResult();
        } catch (IllegalArgumentException e) {
            throw new VirtualShelterException(e);
        }
    }

    @Override
    public List<Animal> listAnimalsAdvertisedByAdvertiser(long advertiserId) throws VirtualShelterException {
        try {
            Query listAnimalQuery = em.createQuery(
                    "SELECT a.animal FROM Advertisement a WHERE a.advertiser.id=:p")
                    .setParameter("p", advertiserId);

            return (List<Animal>) listAnimalQuery.getResultList();
        } catch (IllegalArgumentException e) {
            throw new VirtualShelterException(e);
        }
    }
}
