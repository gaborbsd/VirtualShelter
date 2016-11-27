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

import hu.bme.aut.sportnetwork.rest.resources.UserArg;

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
	
	private String introduction;

	@Column(name = "has_warning")
	private boolean hasWarning;

	@Column(name = "warning_message")
	private String warningMessage;

	@Column(name = "has_notification")
	private boolean hasNotification;

	@Column(name = "deleted")
	private boolean isDeleted;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="owner")
	@JsonIgnore
	private List<SportEvent> ownEvents;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Rating> ratings;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Address address;
	
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
	
	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean getHasNotification() {
		return hasNotification;
	}

	public void setHasNotification(boolean hasNotification) {
		this.hasNotification = hasNotification;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public boolean getHasWarning() {
		return hasWarning;
	}

	public void setHasWarning(boolean hasWarning) {
		this.hasWarning = hasWarning;
	}

	public String getWarningMessage() {
		return warningMessage;
	}

	public void setWarningMessage(String warningMessage) {
		this.warningMessage = warningMessage;
	}

	public boolean getDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
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
	
	public static User toUser(UserArg arg) {
		User u = new User();
		if (arg.getAddress().getAddress() == null || (arg.getAddress().getAddress().isEmpty())) {
			arg.getAddress().setAddress(Address.EMPTY);
		}

		u.setAddress(arg.getAddress());
		u.setAdmin(false);
		u.setAge(arg.getAge());
		u.setEmail(arg.getEmail());
		u.setHasNotification(false);
		u.setHasWarning(false);
		u.setDeleted(false);
		u.setIntroduction(arg.getIntroduction());
		u.setName(arg.getName());
		u.setPassword(arg.getPassword());
		u.setPhoneNumber(arg.getPhoneNumber());
		arg.getInterest().forEach(s -> u.getRatings().add(createRating(u, s)));
		return u;
	}

	public static Rating createRating(User u, Sports s) {
		Rating r = new Rating();
		r.setUser(u);
		r.setSport(s);
		r.setSumValue(0);
		r.setRateNumbers(0);
		return r;
	}
}
