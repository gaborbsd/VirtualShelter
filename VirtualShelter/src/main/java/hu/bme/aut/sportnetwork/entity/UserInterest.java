package hu.bme.aut.sportnetwork.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="interest_of_user", uniqueConstraints={
		@UniqueConstraint(columnNames={"sport","user_id"})
})
public class UserInterest {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private User user;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Sports sport;
	
	@Column(name="degre_of_interest")
	private int degreeOfInterest;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Sports getSport() {
		return sport;
	}

	public void setSport(Sports sport) {
		this.sport = sport;
	}

	public int getDegreeOfInterest() {
		return degreeOfInterest;
	}

	public void setDegreeOfInterest(int degreeOfInterest) {
		this.degreeOfInterest = degreeOfInterest;
	}
	
	
}
