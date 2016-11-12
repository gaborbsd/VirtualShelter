package hu.bme.aut.sportnetwork.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="message_notification")
public class MessageNotification extends Notification {
	
	@ManyToOne
	@JoinColumn(name="conversation_id")
	private Conversation conversation;
	
	@ManyToOne
	@JoinColumn(name = "sender_id")
	private User sender;
	
	public MessageNotification(){}
	
	public MessageNotification(User sender, Conversation conversation) {
		this.sender = sender;
		this.conversation = conversation;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}
	
	
	
	
}
