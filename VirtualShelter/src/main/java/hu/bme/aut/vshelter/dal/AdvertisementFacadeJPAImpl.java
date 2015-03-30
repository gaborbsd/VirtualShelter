package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Advertisement;

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
	public Advertisement findAdvertisementById(int advertisementId) {
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

}
