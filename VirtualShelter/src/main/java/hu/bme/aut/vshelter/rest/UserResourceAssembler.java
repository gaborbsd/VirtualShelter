package hu.bme.aut.vshelter.rest;

import hu.bme.aut.vshelter.entity.Address;
import hu.bme.aut.vshelter.entity.User;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class UserResourceAssembler extends ResourceAssemblerSupport<User, UserResource> {

	public UserResourceAssembler() {
		super(UserController.class, UserResource.class);
	}

	@Override
	public UserResource toResource(User entity) {
		UserResource resource = createResourceWithId(entity.getId(), entity);
		return resource;
	}
	
	protected UserResource instantiateResource(User entity) {
		UserResource resource = new UserResource();
		resource.setName(entity.getName());
		resource.setEmail(entity.getEmail());
		resource.setAddress(entity.getAddress());
		resource.setIntroduction(entity.getIntroductionText());
		return resource;
	}
	
}
