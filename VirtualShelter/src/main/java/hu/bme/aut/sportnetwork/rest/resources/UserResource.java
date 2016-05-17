package hu.bme.aut.sportnetwork.rest.resources;

import org.springframework.hateoas.ResourceSupport;

public class UserResource extends ResourceSupport {
	
	private String name;
	private int age;
	
	public UserResource() {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	

}
