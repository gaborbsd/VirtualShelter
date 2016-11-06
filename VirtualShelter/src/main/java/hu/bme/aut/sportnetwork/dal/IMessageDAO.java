package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import org.springframework.data.domain.Sort;

import hu.bme.aut.sportnetwork.entity.Conversation;
import hu.bme.aut.sportnetwork.entity.Message;

public interface IMessageDAO extends AbstractRepository<Message>{

	List<Message> findByConversation(Conversation c, Sort sort);
}
