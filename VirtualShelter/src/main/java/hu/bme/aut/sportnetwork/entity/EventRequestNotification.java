package hu.bme.aut.sportnetwork.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="event_request_notification", uniqueConstraints={
		@UniqueConstraint(columnNames={"event_id", "sender_id"})
})
public class EventRequestNotification extends EventNotification {
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modification_time")
	private Date modificationTime;

	public EventRequestNotification() {
	}

	public EventRequestNotification(User sender, SportEvent event) {
		this.sender = sender;
		this.event = event;
	}

	public Date getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}


}
