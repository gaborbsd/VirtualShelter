package hu.bme.aut.sportnetwork.dal;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import hu.bme.aut.sportnetwork.entity.SportEvent;

@Repository
public interface SportEventRepository extends GraphRepository<SportEvent> {

	SportEvent findBytitle(String title);

}
