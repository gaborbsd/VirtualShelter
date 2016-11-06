package hu.bme.aut.sportnetwork.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="event_notification")
public class EventNotification extends Notification {
	
	@ManyToOne
	@JoinColumn(name = "event_id")
	private SportEvent event;
	
	@ManyToOne
	@JoinColumn(name = "sender_id")
	private User sender;
	
	public EventNotification(){}
	
	public EventNotification(User sender, SportEvent event) {
		this.sender = sender;
		this.event = event;
	}

	public SportEvent getEvent() {
		return event;
	}

	public void setEvent(SportEvent event) {
		this.event = event;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}
	
	
}
