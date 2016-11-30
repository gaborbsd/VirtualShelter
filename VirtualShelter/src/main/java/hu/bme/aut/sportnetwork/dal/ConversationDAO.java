package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import hu.bme.aut.sportnetwork.entity.Conversation;
import hu.bme.aut.sportnetwork.entity.FriendShip;
import hu.bme.aut.sportnetwork.entity.User;

public interface ConversationDAO extends AbstractRepository<Conversation>, ConversationDAOCustom {
	
	@Query("SELECT c FROM Conversation c WHERE (user1 = ?1 AND user2 = ?2) OR (user2 = ?1 AND user1 = ?2)")
	Conversation getByUser1AndUser2(User user1, User user2);

}
