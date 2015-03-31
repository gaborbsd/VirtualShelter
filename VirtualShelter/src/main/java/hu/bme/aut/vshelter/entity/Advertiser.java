package hu.bme.aut.vshelter.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name = "advertiser", catalog = "vshelter", uniqueConstraints = {
		@UniqueConstraint(columnNames = "email"),
		@UniqueConstraint(columnNames = "name")})
public abstract class Advertiser {
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	protected int id;
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;
	
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	
	@ManyToOne
	private Address address;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Picture> picturesList;
	
	@Column(name = "phoneNumber", unique = false, nullable = true)
	private String phoneNumber;
	
	@Column(name = "mobilePhoneNumber", unique = false, nullable = true)
	private String mobilePhoneNumber;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public List<Picture> getPicturesList() {
		return picturesList;
	}
	
	public void setPicturesList(List<Picture> picturesList) {
		this.picturesList = picturesList;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}
	
	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Advertiser other = (Advertiser) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
