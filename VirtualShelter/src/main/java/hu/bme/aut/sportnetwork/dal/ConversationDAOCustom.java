package hu.bme.aut.sportnetwork.dal;

import hu.bme.aut.sportnetwork.entity.Conversation;
import hu.bme.aut.sportnetwork.entity.User;

public interface ConversationDAOCustom {

	public Conversation getConversationByParticipants(User user1, User user2);
	
}
