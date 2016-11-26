package hu.bme.aut.sportnetwork.rest.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import hu.bme.aut.sportnetwork.entity.Sports;
import hu.bme.aut.sportnetwork.entity.User;
import hu.bme.aut.sportnetwork.rest.UserController;

@Component
public class UserResourceAssembler extends 
		ResourceAssemblerSupport<User, UserResource>{

	public UserResourceAssembler() {
		super(UserController.class, UserResource.class);
	}

	@Override
	public UserResource toResource(User user) {
		UserResource resource = createResourceWithId(user.getId(), user);
		return resource;
	}
	
	@Override
	protected UserResource instantiateResource(User entity) {
		UserResource resource = new UserResource();
		resource.setName(entity.getName());
		resource.setAge(entity.getAge());
		resource.setEmail(entity.getEmail());
		resource.setPhoneNumber(entity.getPhoneNumber());
		resource.setFriendStatus(entity.getFriendStatus());
		/*
		 * List<Sports> interest = new ArrayList<Sports>();
		 * entity.getInterest().forEach(i -> interest.add(i.getSport()));
		 * resource.setInterest(interest);
		 */
		return resource;
	}
}
