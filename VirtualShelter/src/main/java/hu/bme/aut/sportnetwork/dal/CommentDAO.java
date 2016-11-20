package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import org.springframework.data.domain.Sort;

import hu.bme.aut.sportnetwork.entity.Comment;
import hu.bme.aut.sportnetwork.entity.SportEvent;

public interface CommentDAO extends AbstractRepository<Comment>{
	
	List<Comment> findByEvent(SportEvent event, Sort sort);
}
