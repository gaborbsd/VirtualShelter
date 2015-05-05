package hu.bme.aut.vshelter.rest.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import hu.bme.aut.vshelter.entity.User;
import hu.bme.aut.vshelter.rest.UserController;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 *  Converts User entities to UserResource objects
 *  with links.
 * 
 * @author Kiss László
 *
 */
@Component
public class UserResourceAssembler extends ResourceAssemblerSupport<User, UserResource> {

	public UserResourceAssembler() {
		super(UserController.class, UserResource.class);
	}

	/**
	 * Public method for converting a User entity to UserResource object.
	 */
	@Override
	public UserResource toResource(User entity) {
		UserResource resource = createResourceWithId(entity.getId(), entity);
		return resource;
	}
	
	/**
	 * Create the resource object, set it's values and add links to it.
	 */
	@Override
	protected UserResource instantiateResource(User entity) {
		UserResource resource = new UserResource();
		resource.setName(entity.getName());
		resource.setEmail(entity.getEmail());
		resource.setAddress(entity.getAddress());
		resource.setIntroduction(entity.getIntroductionText());
		
		resource.add(linkTo(UserController.class).slash(entity.getId()).slash("institution").withRel("institution"));
		resource.add(linkTo(UserController.class).slash(entity.getId()).slash("advertisement").withRel("advertisement"));
		resource.add(linkTo(UserController.class).slash(entity.getId()).slash("picture").withRel("picture"));
		resource.add(linkTo(UserController.class).slash(entity.getId()).slash("admin").withRel("admin"));
		resource.add(linkTo(UserController.class).slash(entity.getId()).slash("profile").withRel("profile"));
		
		return resource;
	}
	
}
