package hu.bme.aut.sportnetwork.api;

import java.util.List;

import hu.bme.aut.sportnetwork.entity.Conversation;
import hu.bme.aut.sportnetwork.entity.Message;

public interface IMessageOperations {

	public List<Conversation> listConversatinsByUser();
	
	public List<Message> listMessagesbyConversation(long conversationId);
	
	public Conversation writeToConversation(long conversationId, String message);
	
	public Conversation getConversationWithUser(String userName);
}
