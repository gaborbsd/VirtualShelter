package hu.bme.aut.sportnetwork.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.Transient;

import hu.bme.aut.sportnetwork.auth.AuthOperations;
import hu.bme.aut.sportnetwork.auth.Roles;
import hu.bme.aut.sportnetwork.rest.resources.UserArg;

@NodeEntity
public class User {

	@GraphId
	private Long id;

	private int age;

	private String name;

	private String password;

	private String phoneNumber;

	private String salt;

	private String email;

	private boolean hasNotification;

	private boolean hasWarning;

	private String warningMessage;

	private String introduction;

	private String city;

	private String address;

	private String country;

	private boolean isAdmin;

	private boolean deleted;

	@Relationship(type = RelationShipTypes.OWNER_TYPE, direction = Relationship.OUTGOING)
	private Set<SportEvent> ownerOf;

	@Relationship(type = RelationShipTypes.MEMBER_TYPE, direction = Relationship.UNDIRECTED)
	private Set<SportEvent> membersIn;

	@Relationship(type = RelationShipTypes.FRIEND_TYPE, direction = Relationship.OUTGOING)
	private Set<Friend> friends;

	@Relationship(type = RelationShipTypes.MEMBER_TYPE, direction = Relationship.OUTGOING)
	private List<Conversation> conversations;

	@Relationship(type = RelationShipTypes.RATING_TYPE, direction = Relationship.OUTGOING)
	private List<Rating> ratings;

	@Relationship(type = RelationShipTypes.FRIEND_REQUEST_TYPE, direction = Relationship.DIRECTION)
	private List<FriendRequest> friendRequests;

	@Relationship(type = RelationShipTypes.EVENT_REQUEST_TYPE, direction = Relationship.OUTGOING)
	private List<SportEvent> eventApplications;

	@Transient
	private FriendStatus friendStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isHasNotification() {
		return hasNotification;
	}

	public void setHasNotification(boolean hasNotification) {
		this.hasNotification = hasNotification;
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

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Set<SportEvent> getMembersIn() {
		if (membersIn == null) {
			membersIn = new HashSet<>();
		}
		return membersIn;
	}

	public void setMembersIn(Set<SportEvent> membersIn) {
		this.membersIn = membersIn;
	}

	public List<String> getRoles() {
		List<String> ret = new ArrayList<>();
		if (isAdmin) {
			ret.add(Roles.ADMIN);
		}
		ret.add(Roles.USER);
		return ret;

	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Set<SportEvent> getOwnerOf() {
		if (ownerOf == null) {
			ownerOf = new HashSet<>();
		}
		return ownerOf;
	}

	public void setOwnerOf(Set<SportEvent> ownerOf) {
		this.ownerOf = ownerOf;
	}

	public Set<Friend> getFriends() {
		if (friends == null) {
			friends = new HashSet<>();
		}
		return friends;
	}

	public void setFriends(Set<Friend> friends) {
		this.friends = friends;
	}

	public List<Conversation> getConversations() {
		if (conversations == null) {
			conversations = new ArrayList<>();
		}
		return conversations;
	}

	public void setConversations(List<Conversation> conversations) {
		this.conversations = conversations;
	}

	public List<Rating> getRatings() {
		if (ratings == null) {
			return new ArrayList<>();
		}
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		if (ratings == null) {
			ratings = new ArrayList<Rating>();
		}
		this.ratings = ratings;
	}

	public List<FriendRequest> getFriendRequests() {
		if (friendRequests == null) {
			friendRequests = new ArrayList<>();
		}
		return friendRequests;
	}

	public void setFriendRequests(List<FriendRequest> friendRequests) {
		this.friendRequests = friendRequests;
	}

	public List<SportEvent> getEventApplications() {
		if (eventApplications == null) {
			eventApplications = new ArrayList<>();
		}
		return eventApplications;
	}

	public void setEventApplications(List<SportEvent> eventApplications) {
		this.eventApplications = eventApplications;
	}

	public FriendStatus getFriendStatus() {
		return friendStatus;
	}

	public void setFriendStatus(FriendStatus friendStatus) {
		this.friendStatus = friendStatus;
	}

	public static User toUser(UserArg arg) {
		User u = new User();
		u.setAddress(arg.getAddress().getAddress());
		u.setCity(arg.getAddress().getCity());
		u.setAdmin(false);
		u.setAge(arg.getAge());
		u.setDeleted(false);
		u.setEmail(arg.getEmail());
		u.setHasNotification(false);
		u.setHasWarning(false);
		u.setIntroduction(arg.getIntroduction());
		u.setName(arg.getName());
		u.setPhoneNumber(arg.getPhoneNumber());
		u.setPassword(arg.getPassword());
		arg.getInterest().forEach(i -> setRating(u, i));
		setPassword(u);
		return u;

	}

	private static void setRating(User u, Sports i) {
		Rating r = new Rating();
		r.setUser(u);
		r.setSumValue(0);
		r.setRateNumbers(0);
		r.setSport(i);
	}

	private static void setPassword(User u) {
		byte[] salt = AuthOperations.getSalt();
		String securePassword = AuthOperations.get_SHA_1_SecurePassword(u.getPassword(), salt);
		u.setPassword(securePassword);
		u.setSalt(AuthOperations.toHexString(salt));
	}

}
