package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import hu.bme.aut.sportnetwork.entity.Conversation;
import hu.bme.aut.sportnetwork.entity.User;

public interface ConversationDAO {

	Conversation findOne(long id);

	Conversation getByUser1AndUser2(User user1, User user2);
	
	List<Conversation> findByUserAndActive(User user);

	Conversation save(Conversation c);

}
