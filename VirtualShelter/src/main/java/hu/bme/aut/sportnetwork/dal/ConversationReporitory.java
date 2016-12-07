package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import hu.bme.aut.sportnetwork.entity.Conversation;
import hu.bme.aut.sportnetwork.entity.Message;

@Repository
public interface ConversationReporitory extends GraphRepository<Conversation> {

	@Query("MATCH (user:User {name: {0}})-[:MEMBER]->(c:Conversation) WITH c MATCH p=(c)<-[:MEMBER*0..1]-() RETURN p ORDER BY c.lastSendTime")
	List<Conversation> findByParticipants(String userName);

	@Query("MATCH (n:Conversation) WHERE id(n) = {0} WITH n MATCH (n)-[m:WROTE_TO]-(u) RETURN n,m,u ORDER BY m.sendTime DESC")
	Conversation findByMessages(long id);
}
