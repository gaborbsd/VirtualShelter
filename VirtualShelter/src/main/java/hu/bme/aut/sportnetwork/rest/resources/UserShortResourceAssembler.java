package hu.bme.aut.sportnetwork.rest.resources;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import hu.bme.aut.sportnetwork.entity.User;
import hu.bme.aut.sportnetwork.rest.UserController;

@Component
public class UserShortResourceAssembler extends ResourceAssemblerSupport<User, UserShortResource> {

	public UserShortResourceAssembler() {
		super(UserController.class, UserShortResource.class);
	}

	@Override
	public UserShortResource toResource(User entity) {
		UserShortResource resource = createResourceWithId(entity.getId(), entity);
		return resource;
	}

	@Override
	protected UserShortResource instantiateResource(User entity) {
		UserShortResource ret = new UserShortResource();

		/*
		 * ret.setAge(entity.getAge());
		 * ret.setCity(entity.getAddress().getCity());
		 * ret.setUserId(entity.getId()); ret.setName(entity.getName());
		 * ret.setWarned(entity.getHasWarning());
		 * 
		 * StringBuilder b = new StringBuilder(" "); for (int i = 0; i <
		 * entity.getRatings().size() && i < 3; i++) {
		 * b.append(entity.getRatings().get(i).getSport().toString());
		 * b.append(" "); } ret.setInterest(b.toString());
		 */
		return ret;
	}

}
