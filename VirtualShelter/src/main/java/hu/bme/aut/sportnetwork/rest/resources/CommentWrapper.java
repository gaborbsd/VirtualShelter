package hu.bme.aut.sportnetwork.rest.resources;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CommentWrapper {
	
	private UserLinkWrapper writer;
	
	private String message;
	
	private Long id;
	
	private boolean mine;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date date;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isMine() {
		return mine;
	}

	public void setMine(boolean mine) {
		this.mine = mine;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	

	
	

}
