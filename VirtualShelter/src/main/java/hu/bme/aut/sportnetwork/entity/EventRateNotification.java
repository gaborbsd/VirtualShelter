package hu.bme.aut.sportnetwork.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "event_rate_notification")
public class EventRateNotification extends EventNotification {
	
	public static final int EVENT_RATE_NOTIFICATION = 1;

	public static final String EVENT_RATE_MESSAGE = " is closed. Rate the members ";

	public EventRateNotification() {
	}

	public EventRateNotification(SportEvent event) {
		this.event = event;
	}

}
