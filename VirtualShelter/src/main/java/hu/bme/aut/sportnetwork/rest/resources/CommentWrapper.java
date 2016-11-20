package hu.bme.aut.sportnetwork.rest.resources;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CommentWrapper {
	
	private UserLinkWrapper writer;
	
	private String message;
	
	/*@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date date;*/

	public UserLinkWrapper getWriter() {
		return writer;
	}

	public void setWriter(UserLinkWrapper writer) {
		this.writer = writer;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/*public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}*/
	
	

}
