package hu.bme.aut.vshelter.rest.resources;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import hu.bme.aut.vshelter.entity.Advertisement;
import hu.bme.aut.vshelter.rest.AdvertisementController;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * Converts Advertisement entities to AdvertisementResource objects
 * with links.
 * 
 * @author badam
 *
 */
@Component
public class AdvertisementResourceAssembler extends ResourceAssemblerSupport<Advertisement, AdvertisementResource>
{

	public AdvertisementResourceAssembler() {
		super(AdvertisementController.class, AdvertisementResource.class);
	}

	@Override
	public AdvertisementResource toResource(Advertisement entity) {
		return createResourceWithId(entity.getId(), entity);
	}
	
	/**
	 * Create the resource object, set it's values and add links to it.
	 */
	@Override
	protected AdvertisementResource instantiateResource(Advertisement entity) {
		AdvertisementResource resource = new AdvertisementResource();
		resource.setAdvertisementId(entity.getId());
		resource.setAdvertiser(entity.getAdvertiser());
		resource.setAnimal(entity.getAnimal());
		resource.setDateOfAdvertisement(entity.getDateOfAdvertisement());
		resource.setDescription(entity.getDescription());
		resource.setTitle(entity.getTitle());
		
		resource.add(linkTo(AdvertisementController.class).slash(entity.getId()).slash("animal").withRel("animal"));
		resource.add(linkTo(AdvertisementController.class).slash(entity.getId()).slash("profile").withRel("profile"));
		resource.add(linkTo(AdvertisementController.class).slash(entity.getId()).slash("advertiser").withRel("advertiser"));
		return resource;
	}

}
