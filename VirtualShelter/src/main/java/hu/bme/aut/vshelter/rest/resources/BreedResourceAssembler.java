package hu.bme.aut.vshelter.rest.resources;

import hu.bme.aut.vshelter.entity.Breed;
import hu.bme.aut.vshelter.rest.BreedController;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 *  Converts Breed entities to BreedResource objects
 *  with links.
 * 
 * @author Kiss László
 *
 */
@Component
public class BreedResourceAssembler extends ResourceAssemblerSupport<Breed, BreedResource> {

	public BreedResourceAssembler() {
		super(BreedController.class, BreedResource.class);
	}

	/**
	 * Public method for converting an Breed entity to BreedResource object.
	 */
	@Override
	public BreedResource toResource(Breed entity) {
		BreedResource resource = createResourceWithId(entity.getId(), entity);
		return resource;
	}
	
	/**
	 * Create the resource object, set it's values and add links to it.
	 */
	@Override
	public BreedResource instantiateResource(Breed entity) {
		BreedResource resource = new BreedResource();
		resource.setBreedId(entity.getId());
		resource.setName(entity.getBreedName());
		return resource;
	}
	
}
