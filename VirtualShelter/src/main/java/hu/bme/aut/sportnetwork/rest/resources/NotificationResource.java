package hu.bme.aut.sportnetwork.rest.resources;

import org.springframework.hateoas.ResourceSupport;

public class NotificationResource extends ResourceSupport {

	private long notId;

	private String message;

	private UserLinkWrapper sender;

	private int type;

	private long eventId;

	private long conversationId;

	public long getNotId() {
		return notId;
	}

	public void setNotId(long notId) {
		this.notId = notId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserLinkWrapper getSender() {
		return sender;
	}

	public void setSender(UserLinkWrapper sender) {
		this.sender = sender;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public long getConversationId() {
		return conversationId;
	}

	public void setConversationId(long conversationId) {
		this.conversationId = conversationId;
	}


}
