package hu.bme.aut.vshelter.rest;

import hu.bme.aut.vshelter.entity.Address;

import org.springframework.hateoas.ResourceSupport;

public class UserResource extends ResourceSupport{
	private String name;
	private String email;
	private String introduction;
	private Address address;
	
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
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
}
