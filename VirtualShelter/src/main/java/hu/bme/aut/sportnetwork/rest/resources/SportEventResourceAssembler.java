package hu.bme.aut.sportnetwork.rest.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.rest.SportEventController;
import hu.bme.aut.sportnetwork.rest.UserController;

@Component
public class SportEventResourceAssembler extends
	ResourceAssemblerSupport<SportEvent, SportEventResource> {

	public SportEventResourceAssembler() {
		super(SportEventController.class, SportEventResource.class);
	}

	@Override
	public SportEventResource toResource(SportEvent entity) {
		SportEventResource resource = createResourceWithId(entity.getId(), entity);
		return resource;
	}
	
	@Override
	protected SportEventResource instantiateResource(SportEvent entity) {
		SportEventResource resource = new SportEventResource();
		resource.setDate(entity.getDate());
		resource.setType(entity.getType());
		resource.setMaxSize(entity.getMaxSize());
		resource.setDescription(entity.getDescription());
		resource.setOwner(entity.getOwner().getName());
		
		resource.add(linkTo(UserController.class).slash(entity.getOwner().getId()).withRel(String.valueOf(entity.getOwner().getId())));
		return resource;
	}

}
