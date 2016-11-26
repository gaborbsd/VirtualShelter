package hu.bme.aut.sportnetwork.rest.resources;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.sportnetwork.entity.Address;
import hu.bme.aut.sportnetwork.entity.Sports;

public class UserArg {

	private String name;

	private String email;

	private String password;

	private String introduction;

	private String phoneNumber;

	private int age;

	private Address address;

	private List<Sports> interest;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
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

	public List<Sports> getInterest() {
		if (interest == null) {
			interest = new ArrayList<>();
		}
		return interest;
	}

	public void setInterest(List<Sports> interest) {
		this.interest = interest;
	}

}
