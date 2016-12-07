package hu.bme.aut.sportnetwork.entity;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = RelationShipTypes.FRIEND_REQUEST_TYPE)
public class FriendRequest {

	private Long id;

	@StartNode
	private User sender;

	@EndNode
	private User receiver;

	private boolean isDeclined;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public boolean getIsDeclined() {
		return isDeclined;
	}

	public void setIsDeclined(boolean isDeclined) {
		this.isDeclined = isDeclined;
	}

}
