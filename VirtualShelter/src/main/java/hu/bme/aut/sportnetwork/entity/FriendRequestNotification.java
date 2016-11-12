package hu.bme.aut.sportnetwork.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="friend_request_notification", uniqueConstraints={
		@UniqueConstraint(columnNames={"owner_id","sender_id"})
})
public class FriendRequestNotification extends RequestNotification {

	@ManyToOne
	@JoinColumn(name="sender_id")
	private User sender;
	
	public FriendRequestNotification(){}
	
	public FriendRequestNotification(User sender) {
		this.sender = sender;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}
	
	
}
