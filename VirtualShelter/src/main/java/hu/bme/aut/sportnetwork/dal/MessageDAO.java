package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import hu.bme.aut.sportnetwork.entity.Conversation;
import hu.bme.aut.sportnetwork.entity.Message;

public interface MessageDAO {

	List<Message> findByConversation(Conversation c);

	Message save(Message c);
}
