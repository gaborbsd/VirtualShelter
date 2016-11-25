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
	@JoinColumn(name = "user1")
	private User user1;
	
	@ManyToOne
	@JoinColumn(name = "user2")
	private User user2;

	@Column(name = "user1_listen", columnDefinition = "boolean default 0")
	private boolean user1Listen;
	

	@Column(name = "user2_listen", columnDefinition = "boolean default 0")
	private boolean user2Listen;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public boolean getUser1Listen() {
		return user1Listen;
	}

	public void setUser1Listen(boolean user1Listen) {
		this.user1Listen = user1Listen;
	}

	public boolean getUser2Listen() {
		return user2Listen;
	}

	public void setUser2Listen(boolean user2Listen) {
		this.user2Listen = user2Listen;
	}

	
	
}
