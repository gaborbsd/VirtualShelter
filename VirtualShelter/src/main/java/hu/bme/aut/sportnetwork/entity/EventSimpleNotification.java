package hu.bme.aut.sportnetwork.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "event_simple_notification")
public class EventSimpleNotification extends EventNotification {

	public static final String NEW_EVENT = " created a new event ";

	public static final String EVENT_COMMENT = " commented on event ";

	public static final int EVENT_SIMPLE_NOTIFICATION = 3;

	private String message;

	public EventSimpleNotification(){}
	
	public EventSimpleNotification(User sender, SportEvent event, String message) {
		this.sender = sender;

		this.event = event;

		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



}
