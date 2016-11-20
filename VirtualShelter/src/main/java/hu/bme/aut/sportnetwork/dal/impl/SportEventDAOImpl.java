package hu.bme.aut.sportnetwork.dal.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import hu.aut.bme.sportnetwork.api.impl.SportEventFilter;
import hu.bme.aut.sportnetwork.dal.SportEventDAOCustom;
import hu.bme.aut.sportnetwork.entity.Address;
import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.entity.Sports;

public class SportEventDAOImpl implements SportEventDAOCustom {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public SportEvent saveNewEvent(SportEvent e) {
		TypedQuery<Address> addressQuery = em.createQuery("SELECT a FROM Address a WHERE a.city=:city AND a.address=:address", Address.class);
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
	public List<SportEvent> filterPublic(SportEventFilter arg) throws Exception {
		StringBuilder queryString = new StringBuilder("SELECT e ");
		StringBuilder fromString = new StringBuilder("FROM SportEvent e");
		StringBuilder whereLevelString = new StringBuilder();
		StringBuilder whereDataString = new StringBuilder();
		boolean emptyText = arg.getText() == null || arg.getText().isEmpty();
		
		if (emptyText && arg.getSport()==null) {
			addWhereLevelStringClausePart(whereLevelString, arg.getLevelFrom(), arg.getLevelTo(), false);
			
		} else {
			
			addWhereDataStringClausePart(whereDataString, fromString, arg.getSport(), arg.getText(), arg.getCity(), arg.getOwner(), arg.getTitle());
			
			addWhereLevelStringClausePart(whereLevelString, arg.getLevelFrom(), arg.getLevelTo(), true);
		}
		
		queryString.append(fromString.toString());
		queryString.append(" WHERE ");
		if (whereDataString.length() != 0) {
			queryString.append("(");
			queryString.append(whereDataString.toString().substring(4));
			queryString.append(") AND ");
		}
		queryString.append(whereLevelString.toString().substring(5));
		
		String s = queryString.toString();
		
		
		TypedQuery<SportEvent> query = em.createQuery(s, SportEvent.class);
		
		if (!emptyText) {
			query.setParameter("text", "%" + arg.getText().toLowerCase() + "%");
		}
		
		if (arg.getSport() != null) {
			query.setParameter("sport", arg.getSport());
		}
		
		return query.getResultList();
	}
	
	private void addWhereLevelStringClausePart(StringBuilder whereString, int from, int to, boolean allowUndefinedIntervall) {
		if (from == 0 && to == 0) {
			from = allowUndefinedIntervall ? 1 : 11;
		}
		if (from != 0) {
			whereString.append(" AND e.levelIntervalTo >= ");
			whereString.append(Integer.toString(from));
		}
		if (to != 0) {
			whereString.append(" AND e.levelIntervalFrom <= ");
			whereString.append(Integer.toString(to));
		}
	}
	
	private void addWhereDataStringClausePart(StringBuilder whereString, StringBuilder fromString, Sports sport,
			String text, boolean city, boolean owner, boolean title) {
		
		if (city) {
			fromString.append(" JOIN e.address a");
			whereString.append(" OR LOWER(a.city) LIKE :text");
		}
		
		if (owner) {
			fromString.append(" JOIN e.owner o");
			whereString.append(" OR LOWER(o.name) LIKE :text");
		}
		
		if (title) {
			whereString.append(" OR LOWER(e.title) LIKE :text");
		}
		
		if (sport != null) {
			whereString.append(" OR e.type = :sport");
		}

	}

}
