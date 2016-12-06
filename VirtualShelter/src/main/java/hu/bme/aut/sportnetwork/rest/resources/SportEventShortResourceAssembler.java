package hu.bme.aut.sportnetwork.rest.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.rest.SportEventController;
import hu.bme.aut.sportnetwork.rest.UserController;

@Component
public class SportEventShortResourceAssembler extends
ResourceAssemblerSupport<SportEvent, SportEventShortResource> {

	public SportEventShortResourceAssembler() {
		super(SportEventController.class, SportEventShortResource.class);
	}
	
	@Override
	public SportEventShortResource toResource(SportEvent entity) {
		SportEventShortResource resource = createResourceWithId(1, entity);
		return resource;
	}
	
	@Override
	protected SportEventShortResource instantiateResource(SportEvent entity) {
		SportEventShortResource resource = new SportEventShortResource();
		/*
		 * resource.setEventId(entity.getId());
		 * resource.setOwnerId(entity.getOwner().getId());
		 * resource.setOwner(entity.getOwner().getName());
		 * resource.setTitle(entity.getTitle());
		 * resource.setDate(entity.getDate());
		 * resource.setType(entity.getType());
		 * resource.setCity(entity.getAddress().getCity());
		 * resource.setMembers(String.valueOf(entity.getMemberSize()) + "/" +
		 * String.valueOf(entity.getMaxSize()));
		 * resource.setLevels(entity.getLevelIntervalFrom() ==
		 * entity.getLevelIntervalTo() ?
		 * String.valueOf(entity.getLevelIntervalFrom()) :
		 * String.valueOf(entity.getLevelIntervalFrom()) + "-" +
		 * String.valueOf(entity.getLevelIntervalTo()));
		 * 
		 * resource.add(linkTo(UserController.class).slash(entity.getOwner().
		 * getId()).withRel(String.valueOf(entity.getOwner().getId())));
		 */
		return resource;
	}

}
