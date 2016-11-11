package hu.bme.aut.sportnetwork.rest.resources;

import java.util.Date;

import org.springframework.hateoas.ResourceSupport;

public class ConversationResource extends ResourceSupport {

	private String userName;
	
	private Date date;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
