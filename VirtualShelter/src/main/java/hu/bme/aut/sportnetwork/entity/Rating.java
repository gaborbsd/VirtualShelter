package hu.bme.aut.sportnetwork.entity;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Rating {

	@GraphId
	private Long id;

	private Sports sport;

	private int rateNumbers;

	private int sumValue;

	@Relationship(type = RelationShipTypes.RATING_TYPE, direction = Relationship.INCOMING)
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Sports getSport() {
		return sport;
	}

	public void setSport(Sports sport) {
		this.sport = sport;
	}

	public int getRateNumbers() {
		return rateNumbers;
	}

	public void setRateNumbers(int rateNumbers) {
		this.rateNumbers = rateNumbers;
	}

	public int getSumValue() {
		return sumValue;
	}

	public void setSumValue(int sumValue) {
		this.sumValue = sumValue;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
