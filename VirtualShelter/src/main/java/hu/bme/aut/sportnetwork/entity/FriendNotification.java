package hu.bme.aut.sportnetwork.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="friend_notification", uniqueConstraints={
		@UniqueConstraint(columnNames={"owner_id","user_id"})
})
public class FriendNotification extends Notification {

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public FriendNotification(){}
	
	public FriendNotification(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
