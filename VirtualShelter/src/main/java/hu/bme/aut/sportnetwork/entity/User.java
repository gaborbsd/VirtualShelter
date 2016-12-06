package hu.bme.aut.sportnetwork.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

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

	@Relationship(type = RelationShipTypes.MEMBER_TYPE, direction = Relationship.OUTGOING)
	private Set<SportEvent> membersIn;

	@Relationship(type = RelationShipTypes.FRIEND_TYPE, direction = Relationship.OUTGOING)
	private Set<Friend> friends;

	@Relationship(type = RelationShipTypes.MEMBER_TYPE, direction = Relationship.OUTGOING)
	private Set<Conversation> conversations;

	@Relationship(type = RelationShipTypes.OWNER_TYPE, direction = Relationship.INCOMING)
	private Set<Notification> notifications;

	@Relationship(type = RelationShipTypes.NOTIFICATION_SENDER_TYPE, direction = Relationship.OUTGOING)
	private Set<Notification> sendNotification;


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

	public boolean isHasWarning() {
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
		return new ArrayList<>();
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

	public Set<Conversation> getConversations() {
		if (conversations == null) {
			conversations = new HashSet<>();
		}
		return conversations;
	}

	public void setConversations(Set<Conversation> conversations) {
		this.conversations = conversations;
	}

	public Set<Notification> getNotifications() {
		if (notifications == null) {
			notifications = new HashSet<>();
		}
		return notifications;
	}

	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
	}

	public Set<Notification> getSendNotification() {
		if (sendNotification == null) {
			sendNotification = new HashSet<>();
		}
		return sendNotification;
	}

	public void setSendNotification(Set<Notification> sendNotification) {
		this.sendNotification = sendNotification;
	}
}
