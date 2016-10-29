package hu.bme.aut.sportnetwork.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="comment")
public class Comment {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User owner;
	
	@ManyToOne
	private SportEvent event;
	
	@Column
	private String message;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date dateOfComment;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDateOfComment() {
		return dateOfComment;
	}

	public void setDateOfComment(Date dateOfComment) {
		this.dateOfComment = dateOfComment;
	}

	public SportEvent getEvent() {
		return event;
	}

	public void setEvent(SportEvent event) {
		this.event = event;
	}
	
	

}
