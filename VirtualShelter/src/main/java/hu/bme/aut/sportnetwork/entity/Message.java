package hu.bme.aut.sportnetwork.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="message")
public class Message {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(name="message")
	private String message;
	
	@ManyToOne
	@JoinColumn(name = "conversation_id")
	private Conversation conversation;
	
	@ManyToOne
	private User sender;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="send_time")
	private Date sendTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}
	
	
	
	
}
