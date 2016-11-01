package hu.bme.aut.sportnetwork.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="friend_notification")
public class FriendNotification extends Notification {

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
