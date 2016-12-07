package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.entity.User;

@Repository
public interface SportEventRepository extends GraphRepository<SportEvent> {

	List<SportEvent> findByIsOpenedAndIsPublic(boolean isOpened, boolean isPublic);

	List<SportEvent> findByOwner(User owner);

	@Query("MATCH (n) WHERE id(n) = {0} WITH n MATCH p=(n)-[*0..1]-(:Notification {type: 'EVENT_REQUEST'})-[:SENT]-() RETURN p"
			+ " UNION MATCH (n) WHERE id(n) = {0} WITH n MATCH p=(n)-[*0..1]-(m) RETURN p")
	SportEvent findById(Long id);

}
