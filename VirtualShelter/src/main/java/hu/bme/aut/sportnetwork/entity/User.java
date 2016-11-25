package hu.bme.aut.sportnetwork.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(unique=true, nullable=false)
	private String name;
	
	@Column(unique=true, nullable=false)
	@Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
            message="Invalid email format")
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="owner")
	@JsonIgnore
	private List<SportEvent> ownEvents;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="owner")
	@JsonIgnore
	private List<Notification> notifications;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JsonIgnore
	private List<Rating> ratings;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Address address;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="user")
	private List<UserInterest> interest;
	
	@Transient
	private FriendStatus friendStatus;
	
	@Column(name="is_admin", nullable=false)
	private boolean isAdmin;
	
	private String phoneNumber;
	
	private int age;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<SportEvent> getOwnEvents() {
		return ownEvents;
	}

	public void setOwnEvents(List<SportEvent> ownEvents) {
		this.ownEvents = ownEvents;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Notification> getNotifications() {
		if (notifications==null) {
			notifications = new ArrayList<>();
		}
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public List<Rating> getRatings() {
		if (ratings==null) {
			ratings = new ArrayList<>();
		}
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	
	public FriendStatus getFriendStatus() {
		return friendStatus;
	}

	public void setFriendStatus(FriendStatus friendStatus) {
		this.friendStatus = friendStatus;
	}

	public List<UserInterest> getInterest() {
		return interest;
	}

	public void setInterest(List<UserInterest> interest) {
		this.interest = interest;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
