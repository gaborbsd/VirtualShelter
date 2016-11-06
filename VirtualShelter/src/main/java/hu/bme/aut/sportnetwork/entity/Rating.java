package hu.bme.aut.sportnetwork.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="rating", uniqueConstraints={
		@UniqueConstraint(columnNames={"sport", "user_id"})
})
public class Rating {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable=false)
	private User user;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Sports sport;
	
	@Column(name = "sum_value", nullable=false)
	private int sumValue;
	
	@Column(name = "rate_numbers", nullable=false)
	private int rateNumbers;

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

	public int getSumValue() {
		return sumValue;
	}

	public void setSumValue(int sumValue) {
		this.sumValue = sumValue;
	}

	public int getRateNumbers() {
		return rateNumbers;
	}

	public void setRateNumbers(int rateNumbers) {
		this.rateNumbers = rateNumbers;
	}
	
	

}
