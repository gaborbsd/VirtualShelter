package hu.bme.aut.sportnetwork.entity;

import java.util.Date;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.typeconversion.DateString;

@RelationshipEntity(type = RelationShipTypes.COMMENT_TYPE)
public class Comment {

	@GraphId
	private Long id;

	@StartNode
	private User user;

	@EndNode
	private SportEvent event;

	private String message;

	@DateString("yy-MM-dd HH:mm:ss")
	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SportEvent getEvent() {
		return event;
	}

	public void setEvent(SportEvent event) {
		this.event = event;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
