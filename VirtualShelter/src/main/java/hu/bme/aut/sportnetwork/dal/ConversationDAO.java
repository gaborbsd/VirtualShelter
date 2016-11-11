package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import org.springframework.data.domain.Sort;

import hu.bme.aut.sportnetwork.entity.Conversation;
import hu.bme.aut.sportnetwork.entity.User;

public interface ConversationDAO extends AbstractRepository<Conversation>, ConversationDAOCustom {
	
	List<Conversation> findByUser1AndActiveTrueOrUser2AndActiveTrue(User user1, User user2, Sort sort);

}
