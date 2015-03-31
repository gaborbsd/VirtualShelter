package hu.bme.aut.vshelter.rest.resources;

import org.springframework.hateoas.ResourceSupport;

public class BreedResource extends ResourceSupport {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
