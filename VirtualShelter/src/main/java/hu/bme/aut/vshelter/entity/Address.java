package hu.bme.aut.vshelter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address", catalog = "vshelter")
public class Address {
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	
	@Column(name = "country", unique = false, nullable = false)
	private String country;
	
	@Column(name = "state", unique = false, nullable = true)
	private String state;
	
	@Column(name = "province", unique = false, nullable = true)
	private String province;
	
	@Column(name = "city", unique = false, nullable = false)
	private String city;
	
	@Column(name = "zipCode", unique = false, nullable = false)
	private int zipCode;
	
	@Column(name = "address", unique = false, nullable = false)
	private String address;
	
	@Column(name = "latitude", unique = false, nullable = true)
	private double latitude;
	
	@Column(name = "longitude", unique = false, nullable = true)
	private double longitude;
	
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
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
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
	
	public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int)id;
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
		if (country.equals(other.country) && 
				address.equals(other.address) &&
				city.equals(other.city) &&
				latitude == other.latitude &&
				longitude == other.longitude &&
				province.equals(other.province) &&
				state.equals(other.state) &&
				zipCode == other.zipCode)
			return true;
		return false;
	}
}
