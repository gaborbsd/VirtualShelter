package hu.bme.aut.sportnetwork.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="address", uniqueConstraints={
		@UniqueConstraint(columnNames={"city", "address"})
})
public class Address {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String country;
	
	private String province;
	
	@Column(nullable = false)
	private String city;
	
	@Pattern(regexp="\\d{4,6}", 
				message="Invalid zipcode format")
	private int zipCode;
	
	@Column(nullable=false)
	private String address;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="address")
	private List<SportEvent> events;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	

	public List<SportEvent> getEvents() {
		return events;
	}

	public void setEvents(List<SportEvent> events) {
		this.events = events;
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
		Address other = (Address) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
