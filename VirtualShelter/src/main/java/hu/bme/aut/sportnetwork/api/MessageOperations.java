package hu.bme.aut.sportnetwork.api;

import java.util.List;

import hu.bme.aut.sportnetwork.entity.Conversation;
import hu.bme.aut.sportnetwork.entity.Message;

public interface MessageOperations {

	public List<Conversation> listConversatinsByUser();
	
	public List<Message> listMessagesbyConversation(long conversationId) throws SportNetworkException;
	
	public List<Message> writeToConversation(long conversationId, String message) throws SportNetworkException;
	
	public Conversation getConversationWithUser(String userName);
}
