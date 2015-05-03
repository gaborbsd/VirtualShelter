package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.dal.AdvertisementFacade;
import hu.bme.aut.vshelter.entity.Advertisement;
import hu.bme.aut.vshelter.entity.Advertiser;
import hu.bme.aut.vshelter.entity.Animal;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.transaction.Transactional;
import javax.persistence.TypedQuery;

public class AdvertisementFacadeJPAImpl implements AdvertisementFacade {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public Advertisement findById(long advertisementId) throws VirtualShelterException {
		try {
			return em.find(Advertisement.class, advertisementId);
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		}
	}

	@Override
	@Transactional
	public List<Advertisement> findAll() throws VirtualShelterException {
		try {
			TypedQuery<Advertisement> query = em.createQuery("SELECT a FROM Advertisement a",
					Advertisement.class);
			
			return query.getResultList();
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		}
	}

	@Override
	@Transactional
	public void create(Advertisement advertisement) throws VirtualShelterException {
		try {
			em.persist(advertisement);
		} catch (EntityExistsException e) {
			throw new VirtualShelterException(e);
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		} catch (TransactionRequiredException e) {
			throw new VirtualShelterException(e);
		}
	}

	@Override
	@Transactional
	public void edit(Advertisement advertisement) throws VirtualShelterException {
		try {
			em.merge(advertisement);
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		} catch (TransactionRequiredException e) {
			throw new VirtualShelterException(e);
		}
	}

	@Override
	@Transactional
	public void deleteById(long advertisementId) throws VirtualShelterException {
		try {
			Query deleteQuery = em.createQuery(
					"DELETE FROM Advertisement where id=:p")
					.setParameter("p", advertisementId);
			deleteQuery.executeUpdate();
		} catch (IllegalArgumentException e) {
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
			
			return (Advertiser)getAdvertiserOfAnimalQuery.getSingleResult();
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
			
			return (List<Animal>)listAnimalQuery.getResultList();
		} catch (IllegalArgumentException e) {
			throw new VirtualShelterException(e);
		} 
	}
}
