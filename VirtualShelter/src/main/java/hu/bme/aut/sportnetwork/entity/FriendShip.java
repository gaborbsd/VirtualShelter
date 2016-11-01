package hu.bme.aut.sportnetwork.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="friendship")
public class FriendShip {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name="person")
	private User person;
	
	@ManyToOne
	@JoinColumn(name="friend")
	private User friend;
	
	@Column(name="listen_notfications", columnDefinition="boolean default 0")
	private boolean listenNotifications;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getPerson() {
		return person;
	}

	public void setPerson(User person) {
		this.person = person;
	}

	public User getFriend() {
		return friend;
	}

	public void setFriend(User friend) {
		this.friend = friend;
	}

	public boolean isListenNotifications() {
		return listenNotifications;
	}

	public void setListenNotifications(boolean listenNotifications) {
		this.listenNotifications = listenNotifications;
	}
	
	
}
