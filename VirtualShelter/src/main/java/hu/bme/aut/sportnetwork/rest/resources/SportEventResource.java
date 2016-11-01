package hu.bme.aut.sportnetwork.rest.resources;

import java.util.Calendar;
import java.util.Date;

import org.springframework.hateoas.ResourceSupport;

import hu.bme.aut.sportnetwork.entity.Sports;
import hu.bme.aut.sportnetwork.entity.User;

public class SportEventResource extends ResourceSupport {
	
	private User owner;
	
	private Date date;

	private Sports type;
	
	private int maxSize;
	
	private String description;
	
	public SportEventResource() {
		
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
