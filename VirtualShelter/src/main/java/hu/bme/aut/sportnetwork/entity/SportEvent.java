package hu.bme.aut.sportnetwork.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.Transient;
import org.neo4j.ogm.annotation.typeconversion.DateString;

@NodeEntity
public class SportEvent {

	@GraphId
	private Long id;

	private String title;

	private String description;

	@DateString("yy-MM-dd HH:mm:ss")
	private Date date;

	@Relationship(type = RelationShipTypes.ADDRESS_TYPE, direction = Relationship.OUTGOING)
	private Address address;

	@Relationship(type = RelationShipTypes.OWNER_TYPE, direction = Relationship.INCOMING)
	private User owner;

	private boolean isOpened;

	private boolean isPublic;

	private int levelFrom;

	private int levelTo;

	private Sports type;

	private int maxSize;

	@Relationship(type = RelationShipTypes.MEMBER_TYPE, direction = Relationship.UNDIRECTED)
	private Set<User> members;

	@Relationship(type = RelationShipTypes.COMMENT_TYPE, direction = Relationship.INCOMING)
	private List<Comment> comments;

	@Relationship(type = RelationShipTypes.EVENT_REQUEST_TYPE, direction = Relationship.INCOMING)
	private List<User> applications;

	@Transient
	private EventStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean getIsOpened() {
		return isOpened;
	}

	public void setIsOpened(boolean isOpened) {
		this.isOpened = isOpened;
	}

	public boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public int getLevelFrom() {
		return levelFrom;
	}

	public void setLevelFrom(int levelFrom) {
		this.levelFrom = levelFrom;
	}

	public int getLevelTo() {
		return levelTo;
	}

	public void setLevelTo(int levelTo) {
		this.levelTo = levelTo;
	}

	public Sports getType() {
		return type;
	}

	public void setType(Sports type) {
		this.type = type;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public Set<User> getMembers() {
		if (members == null) {
			members = new HashSet<>();
		}
		return members;
	}

	public void setMembers(Set<User> members) {
		this.members = members;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<Comment> getComments() {
		if (comments == null) {
			comments = new ArrayList<>();
		}
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<User> getApplications() {
		if (applications == null) {
			applications = new ArrayList<>();
		}
		return applications;
	}

	public void setApplications(List<User> applications) {
		this.applications = applications;
	}

	public EventStatus getStatus() {
		return status;
	}

	public void setStatus(EventStatus status) {
		this.status = status;
	}

}
