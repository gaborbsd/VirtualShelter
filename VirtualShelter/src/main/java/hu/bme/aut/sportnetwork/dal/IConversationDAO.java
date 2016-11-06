package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import org.springframework.data.domain.Sort;

import hu.bme.aut.sportnetwork.entity.Conversation;
import hu.bme.aut.sportnetwork.entity.User;

public interface IConversationDAO extends AbstractRepository<Conversation>, ConversationDAOCustom {
	
	List<Conversation> findByUser1OrUser2(User user1, User user2, Sort sort);

}
