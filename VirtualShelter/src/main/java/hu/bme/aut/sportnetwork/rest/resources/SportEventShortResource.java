package hu.bme.aut.sportnetwork.rest.resources;

import java.util.Date;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonFormat;

import hu.bme.aut.sportnetwork.entity.Sports;

public class SportEventShortResource extends ResourceSupport {
	
	private String owner;
	
	private String title;
	
	private long ownerId;
	
	private long eventId; 
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date date;
	
	private String members;
	
	private Sports type;
	
	private String levels;
	
	private String city;

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public long getOwnerId() {
		return ownerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMembers() {
		return members;
	}

	public void setMembers(String members) {
		this.members = members;
	}

	public String getLevels() {
		return levels;
	}

	public void setLevels(String levels) {
		this.levels = levels;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Sports getType() {
		return type;
	}

	public void setType(Sports type) {
		this.type = type;
	}

}
