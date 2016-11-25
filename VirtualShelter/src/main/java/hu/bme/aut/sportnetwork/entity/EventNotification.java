package hu.bme.aut.sportnetwork.entity;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class EventNotification extends Notification {

	@ManyToOne
	@JoinColumn(name = "event_id")
	protected SportEvent event;

	public SportEvent getEvent() {
		return event;
	}

	public void setEvent(SportEvent event) {
		this.event = event;
	}
}
