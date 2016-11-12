package hu.bme.aut.sportnetwork.rest.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import hu.bme.aut.sportnetwork.entity.FriendStatus;
import hu.bme.aut.sportnetwork.entity.Sports;

public class UserResource extends ResourceSupport {
	
	private String name;
	private int age;
	private String email;
	private String phoneNumber;
	private FriendStatus friendStatus;
	private List<Sports> interest;
	
	public UserResource() {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public FriendStatus getFriendStatus() {
		return friendStatus;
	}

	public void setFriendStatus(FriendStatus friendStatus) {
		this.friendStatus = friendStatus;
	}

	public List<Sports> getInterest() {
		if (interest == null) {
			return new ArrayList<>();
		}
		return interest;
	}

	public void setInterest(List<Sports> interest) {
		this.interest = interest;
	}
	
	
	
}
