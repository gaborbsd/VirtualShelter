package hu.bme.aut.sportnetwork.entity;

import java.util.Date;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateString;

@NodeEntity
public class Notification {

	public static final int FRIEND_REQUEST_NOTIFICATION = 4;

	public static final String FRIEND_REQUEST_MESSAGE = " wants to be your friend ";

	public static final int MESSAGE_NOTIFICATION = 5;

	public static final String MESSAGE_MESSAGE = " wrote to you ";

	public static final String NEW_EVENT = " created a new event ";

	public static final String EVENT_COMMENT = " commented on event ";

	public static final int EVENT_SIMPLE_NOTIFICATION = 3;

	public static final int EVENT_REQUEST_NOTIFICATION = 2;

	public static final String EVENT_REQUEST_MESSAGE = " wants to join your ";

	public static final int EVENT_RATE_NOTIFICATION = 1;

	public static final String EVENT_RATE_MESSAGE = " is closed. Rate the members ";

	public static final String FIELD_DATE = "sendTime";

	@GraphId
	private Long id;

	@Relationship(type = RelationShipTypes.OWNER_TYPE, direction = Relationship.OUTGOING)
	private User owner;

	@Relationship(type = RelationShipTypes.NOTIFICATION_SENDER_TYPE, direction = Relationship.INCOMING)
	private User sender;

	@Relationship(type = RelationShipTypes.NOTIFICATION_NEWS_TYPE, direction = Relationship.OUTGOING)
	private SportEvent event;

	@Relationship(type = RelationShipTypes.NOTIFICATION_NEWS_TYPE, direction = Relationship.OUTGOING)
	private Conversation conversation;

	private boolean isDeclined;

	private String message;

	@DateString("yy-MM-dd HH:mm:ss")
	private Date sendTime;

	@DateString("yy-MM-dd HH:mm:ss")
	private Date modificationTime;

	private NotificationType type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public SportEvent getEvent() {
		return event;
	}

	public void setEvent(SportEvent event) {
		this.event = event;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public boolean getIsDeclined() {
		return isDeclined;
	}

	public void setIsDeclined(boolean isDeclined) {
		this.isDeclined = isDeclined;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Date getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public NotificationType getType() {
		return type;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}

}
