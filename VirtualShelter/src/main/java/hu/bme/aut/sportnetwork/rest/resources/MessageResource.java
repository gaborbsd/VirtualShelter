package hu.bme.aut.sportnetwork.rest.resources;

import java.util.Date;

import org.springframework.hateoas.ResourceSupport;

public class MessageResource extends ResourceSupport {
	
	private String message;
	
	private Date date;
	
	private String sender;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
	
	
 
}
