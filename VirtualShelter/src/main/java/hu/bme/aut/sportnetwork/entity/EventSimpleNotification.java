package hu.bme.aut.sportnetwork.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "event_simple_notification")
public class EventSimpleNotification extends EventNotification {

	public EventSimpleNotification(){}
	
	public EventSimpleNotification(User sender, SportEvent event) {
		this.sender = sender;

		this.event = event;
	}



}
