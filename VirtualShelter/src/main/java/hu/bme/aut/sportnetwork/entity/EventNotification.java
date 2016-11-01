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

	public SportEvent getEvent() {
		return event;
	}

	public void setEvent(SportEvent event) {
		this.event = event;
	}
	
	
}
