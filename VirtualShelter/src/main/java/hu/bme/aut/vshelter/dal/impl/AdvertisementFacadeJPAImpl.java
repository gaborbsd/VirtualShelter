package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.dal.AdvertisementFacade;
import hu.bme.aut.vshelter.entity.Advertisement;
import hu.bme.aut.vshelter.entity.Advertiser;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.persistence.TypedQuery;

public class AdvertisementFacadeJPAImpl implements AdvertisementFacade {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public Advertisement findAdvertisementById(long advertisementId) {
		return em.find(Advertisement.class, advertisementId);
	}

	@Override
	@Transactional
	public List<Advertisement> findAll() {
		TypedQuery<Advertisement> query = em.createQuery("SELECT a FROM Advertisement a",
				Advertisement.class);
		
		return query.getResultList();
	}

	@Override
	@Transactional
	public void create(Advertisement advertisement) {
		em.persist(advertisement);
	}

	@Override
	@Transactional
	public void edit(Advertisement advertisement) {
		em.merge(advertisement);
	}

	@Override
	@Transactional
	public void deleteAdvertisementById(long advertisementId) {
		Query deleteQuery = em.createQuery(
				"DELETE FROM Advertisement where id=:p")
				.setParameter("p", advertisementId);
		deleteQuery.executeUpdate();
	}

	@Override
	public List<Advertisement> listAdvertisementsFromAdvertiser(
			long advertiserId) {
		TypedQuery<Advertisement> query = em.createQuery("SELECT a FROM Advertisement a where advertiser_id=:p",
				Advertisement.class).setParameter("p", advertiserId);
		return query.getResultList();
	}

	@Override
	public Advertiser getAdvertiserOfAnimal(long animalId) {
		Query query2 = em.createQuery(
				"SELECT a.advertiser FROM Advertisement a WHERE a.animal.id=:p")
				.setParameter("p", Long.valueOf(2));
		
		return (Advertiser)query2.getSingleResult();
	}
}
