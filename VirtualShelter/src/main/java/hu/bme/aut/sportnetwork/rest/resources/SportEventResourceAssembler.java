package hu.bme.aut.sportnetwork.rest.resources;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.rest.SportEventController;

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
		resource.setOwner(entity.getOwner());
		resource.setDate(entity.getDate());
		resource.setType(entity.getType());
		resource.setMaxSize(entity.getMaxSize());
		resource.setDescription(entity.getDescription());
		
		return resource;
	}

}
