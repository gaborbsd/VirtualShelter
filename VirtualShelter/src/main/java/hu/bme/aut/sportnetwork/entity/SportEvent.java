package hu.bme.aut.sportnetwork.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SportEvent {
	
	@Id
	@GeneratedValue
	private long id;
		
	@ManyToOne(fetch = FetchType.EAGER)
	private User owner;
	
	@Column(nullable=false)
	private Calendar date;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Sports type;
	
	private int maxSize;
	
	private String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Sports getType() {
		return type;
	}

	public void setType(Sports type) {
		this.type = type;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SportEvent other = (SportEvent) obj;
		if (id != other.id)
			return false;
		return true;
	}

	

}
