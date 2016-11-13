package hu.bme.aut.sportnetwork.rest.resources;

import java.util.Date;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ConversationResource extends ResourceSupport {
	
	private long publicId;
	
	private String userName;
	
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date date;
	
	private String message;

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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getPublicId() {
		return publicId;
	}

	public void setPublicId(long publicId) {
		this.publicId = publicId;
	}
	
}
