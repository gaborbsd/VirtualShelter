package hu.bme.aut.sportnetwork.rest.resources;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonFormat;

import hu.bme.aut.sportnetwork.entity.Sports;

public class SportEventResource extends ResourceSupport {
	
	private String owner;
	
	private String title;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date date;

	private Sports type;
	
	private int maxSize;
	
	private String description;
	
	private List<CommentWrapper> comments;
	
	private List<UserLinkWrapper> members;
	
	public SportEventResource() {
		
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<CommentWrapper> getComments() {
		if (comments == null) {
			comments = new ArrayList<>();
		}
		return comments;
	}

	public void setComments(List<CommentWrapper> comments) {
		this.comments = comments;
	}

	public List<UserLinkWrapper> getMembers() {
		if (members == null) {
			members = new ArrayList<>();
		}
		return members;
	}

	public void setMembers(List<UserLinkWrapper> members) {
		this.members = members;
	}
	
	
	
	

}
