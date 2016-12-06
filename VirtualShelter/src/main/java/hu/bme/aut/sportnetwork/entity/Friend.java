package hu.bme.aut.sportnetwork.entity;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = RelationShipTypes.FRIEND_TYPE)
public class Friend {

	@GraphId
	private Long id;

	@StartNode
	private User user;

	@EndNode
	private User friend;

	private boolean listenToNotifications;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getFriend() {
		return friend;
	}

	public void setFriend(User friend) {
		this.friend = friend;
	}

	public boolean isListenToNotifications() {
		return listenToNotifications;
	}

	public void setListenToNotifications(boolean listenToNotifications) {
		this.listenToNotifications = listenToNotifications;
	}

}
