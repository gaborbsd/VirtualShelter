package hu.bme.aut.vshelter.rest;

import org.springframework.hateoas.ResourceSupport;

public class AnimalResource extends ResourceSupport {
	private String name;

	public AnimalResource() {
	}

	public AnimalResource(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
