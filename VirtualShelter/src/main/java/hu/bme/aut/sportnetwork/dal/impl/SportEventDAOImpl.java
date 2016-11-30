package hu.bme.aut.sportnetwork.dal.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import hu.aut.bme.sportnetwork.api.impl.SportEventFilter;
import hu.bme.aut.sportnetwork.dal.SportEventDAOCustom;
import hu.bme.aut.sportnetwork.entity.Address;
import hu.bme.aut.sportnetwork.entity.Address_;
import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.entity.SportEvent_;
import hu.bme.aut.sportnetwork.entity.User_;

public class SportEventDAOImpl implements SportEventDAOCustom {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public SportEvent saveNewEvent(SportEvent e) {
		TypedQuery<Address> addressQuery = em.createQuery(
				"SELECT a FROM Address a WHERE a.country=:country AND a.city=:city AND a.address=:address",
				Address.class);
		addressQuery.setParameter("country", e.getAddress().getCountry());
		addressQuery.setParameter("city", e.getAddress().getCity());
		addressQuery.setParameter("address", e.getAddress().getAddress());
		
		Address a = null;
		try {
			a = addressQuery.getSingleResult();
		} catch (NoResultException ex) {
			
		}
		
		SportEvent ret = null;
		
		if (a != null) {
			e.setAddress(a);
			ret = em.merge(e);
		} else {
			em.persist(e);
			ret = e;
		}
		
		return ret;
	}

	@Override
	public List<SportEvent> filterPublic(SportEventFilter arg) {
		boolean isFilterText = arg.getCity() || arg.getOwner() || arg.getTitle();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<SportEvent> cq = cb.createQuery(SportEvent.class);
		Root<SportEvent> e = cq.from(SportEvent.class);
		cq.select(e);
		
		List<Predicate> andPredicates = new ArrayList<>();
		
		Predicate opened = cb.equal(e.get(SportEvent_.isOpened), true);
		andPredicates.add(opened);
		
		if (arg.isLevelFrom()) {
			Predicate greaterThenFrom = cb.greaterThanOrEqualTo(e.get(SportEvent_.levelIntervalTo), arg.getLevelFrom());
			andPredicates.add(greaterThenFrom);
		}
		if (arg.isLevelTo()) {
			Predicate lessThenTo = cb.lessThanOrEqualTo(e.get(SportEvent_.levelIntervalFrom), arg.getLevelTo());
			andPredicates.add(lessThenTo);
		}

		List<Predicate> orPredicates = new ArrayList<>();

		if (arg.getSport() != null) {
			Predicate equalSport = cb.equal(e.get(SportEvent_.type), arg.getSport());
			orPredicates.add(equalSport);
		}

		ParameterExpression<String> p = cb.parameter(String.class);

		if (isFilterText) {

			if (arg.getCity()) {
				Predicate cityPredicate = cb.like(e.join(SportEvent_.address).get(Address_.city), p);
				orPredicates.add(cityPredicate);
			}

			if (arg.getOwner()) {
				Predicate ownerPredicate = cb.like(e.join(SportEvent_.owner).get(User_.name), p);
				orPredicates.add(ownerPredicate);
			}

			if (arg.getTitle()) {
				Predicate titlePredicate = cb.like(e.get(SportEvent_.title), p);
				orPredicates.add(titlePredicate);
			}
		}

		if (!orPredicates.isEmpty()) {
			Predicate[] disjArray = new Predicate[orPredicates.size()];
			orPredicates.toArray(disjArray);
			Predicate disjunction = cb.or(disjArray);
			andPredicates.add(disjunction);
		}

		Predicate[] conjArray = new Predicate[andPredicates.size()];
		andPredicates.toArray(conjArray);
		Predicate conjunction = cb.and(conjArray);

		cq.where(conjunction);

		TypedQuery<SportEvent> query = em.createQuery(cq);


		if (isFilterText) {
			query.setParameter(p, toLikeString(arg));
		}

		String queryString = query.unwrap(org.hibernate.Query.class).getQueryString();

		System.out.println(queryString);

		return query.getResultList();
	}

	private String toLikeString(SportEventFilter arg) {
		return "%" + arg.getText() + "%";
	}

}
