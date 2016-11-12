package hu.bme.aut.sportnetwork.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class RequestNotification extends Notification {

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modification_time")
	private Date modificationTime;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private NotificationStatus status;
	
	public Date getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}

	public NotificationStatus getStatus() {
		return status;
	}

	public void setStatus(NotificationStatus status) {
		this.status = status;
	}
}
