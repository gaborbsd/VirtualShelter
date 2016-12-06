package hu.bme.aut.sportnetwork.dal;

import java.util.List;

import hu.bme.aut.sportnetwork.entity.Comment;
import hu.bme.aut.sportnetwork.entity.SportEvent;

public interface CommentDAO {
	
	List<Comment> findByEvent(SportEvent event);
}
