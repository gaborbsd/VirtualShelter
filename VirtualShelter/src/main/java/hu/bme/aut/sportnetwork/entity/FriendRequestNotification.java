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
@Table(name="friend_request_notification", uniqueConstraints={
		@UniqueConstraint(columnNames={"owner_id","sender_id"})
})
public class FriendRequestNotification extends Notification {

	public static final int FRIEND_REQUEST_NOTIFICATION = 4;

	public static final String FRIEND_REQUEST_MESSAGE = " wants to be your friend ";

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modification_time")
	private Date modificationTime;

	public FriendRequestNotification(){}
	
	public FriendRequestNotification(User sender) {
		this.sender = sender;
	}

	public Date getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}
	

}
