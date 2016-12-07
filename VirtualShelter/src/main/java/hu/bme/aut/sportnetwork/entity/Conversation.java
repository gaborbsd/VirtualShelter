package hu.bme.aut.sportnetwork.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateString;

@NodeEntity
public class Conversation {

	@GraphId
	private Long id;

	@Relationship(type = RelationShipTypes.MEMBER_TYPE, direction = Relationship.INCOMING)
	private Set<User> participants;

	@Relationship(type = RelationShipTypes.MESSAGE_TYPE, direction = Relationship.INCOMING)
	private List<Message> messages;

	private boolean isActive;

	private String lastMessage;

	private int memberSize;

	@DateString("yy-MM-dd HH:mm:ss")
	private Date lastSendTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<User> getParticipants() {
		if (participants == null) {
			participants = new HashSet<>();
		}
		return participants;
	}

	public void setParticipants(Set<User> participants) {
		this.participants = participants;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getLastMessage() {
		return lastMessage;
	}

	public void setLastMessage(String lastMessage) {
		this.lastMessage = lastMessage;
	}

	public Date getLastSendTime() {
		return lastSendTime;
	}

	public void setLastSendTime(Date lastSendTime) {
		this.lastSendTime = lastSendTime;
	}

	public List<Message> getMessages() {
		if (messages == null) {
			messages = new ArrayList<>();
		}
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public int getMemberSize() {
		return memberSize;
	}

	public void setMemberSize(int memberSize) {
		this.memberSize = memberSize;
	}

}
