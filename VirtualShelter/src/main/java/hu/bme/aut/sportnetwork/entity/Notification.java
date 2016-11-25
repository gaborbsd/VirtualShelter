package hu.bme.aut.sportnetwork.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Notification {
	
	@TableGenerator(name = "notification_gen", table = "id_gen", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "notification_gen")
	protected long notificationId;
	
	@ManyToOne
	@JoinColumn(name="owner_id", nullable=false)
	protected User owner;
	
	protected String message;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="send_time")
	protected Date sendTime;
	
	@ManyToOne
	@JoinColumn(name = "sender_id")
	protected User sender;

	@Column(name = "declined")
	private boolean isDeclined;

	public long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(long notificationId) {
		this.notificationId = notificationId;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public boolean getIsDeclined() {
		return isDeclined;
	}

	public void setIsDeclined(boolean isDeclined) {
		this.isDeclined = isDeclined;
	}
	
	

}
