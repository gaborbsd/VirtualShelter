package hu.bme.aut.sportnetwork.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="sportevent")
public class SportEvent {
	
	@Id
	@GeneratedValue
	private long id;
		
	@ManyToOne
	private User owner;
	
	@OneToMany(mappedBy="event")
	private List<Comment> comments;
	
	@ManyToMany
	@JoinTable(name="members_of_sportevent", 
				joinColumns=@JoinColumn(name="sportevent_id"),
				inverseJoinColumns=@JoinColumn(name="user_id"))
	private List<User> members;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Address address;
	
	@Column(name = "member_size", nullable = false)
	private int memberSize;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Sports type;
	
	@Column(name = "max_size", nullable = false)
	private int maxSize;
	
	@Column(name = "is_public", nullable = false)
	private boolean isPublic;
	
	@Column(name = "level_from", nullable = false)
	private int levelIntervalFrom;
	
	@Column(name = "level_to", nullable = false)
	private int levelIntervalTo;
	
	private String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	
	public List<User> getMembers() {
		if (members == null) {
			members = new ArrayList<>();
		}
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getMemberSize() {
		return memberSize;
	}

	public void setMemberSize(int memberSize) {
		this.memberSize = memberSize;
	}	

	public int getLevelIntervalFrom() {
		return levelIntervalFrom;
	}

	public void setLevelIntervalFrom(int levelIntervalFrom) {
		this.levelIntervalFrom = levelIntervalFrom;
	}

	public int getLevelIntervalTo() {
		return levelIntervalTo;
	}

	public void setLevelIntervalTo(int levelIntervalTo) {
		this.levelIntervalTo = levelIntervalTo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SportEvent other = (SportEvent) obj;
		if (id != other.id)
			return false;
		return true;
	}

	

}
