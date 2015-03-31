package hu.bme.aut.vshelter.rest.resources;

import hu.bme.aut.vshelter.entity.Breed;
import hu.bme.aut.vshelter.rest.BreedController;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class BreedResourceAssembler extends ResourceAssemblerSupport<Breed, BreedResource> {

	public BreedResourceAssembler() {
		super(BreedController.class, BreedResource.class);
	}

	@Override
	public BreedResource toResource(Breed entity) {
		BreedResource resource = createResourceWithId(entity.getId(), entity);
		return resource;
	}
	
	@Override
	public BreedResource instantiateResource(Breed entity) {
		BreedResource resource = new BreedResource();
		resource.setName(entity.getBreedName());
		return resource;
	}
	
}
