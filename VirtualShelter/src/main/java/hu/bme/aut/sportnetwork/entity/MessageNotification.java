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

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
	
	
}
