package hu.bme.aut.sportnetwork.rest;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import hu.bme.aut.sportnetwork.entity.Animal;

@Component
public class AnimalResourceAssembler extends
		ResourceAssemblerSupport<Animal, AnimalResource> {

	public AnimalResourceAssembler() {
		super(AnimalController.class, AnimalResource.class);
	}

	@Override
	public AnimalResource toResource(Animal animal) {
		AnimalResource resource = createResourceWithId(animal.getId(), animal);
		return resource;
	}

	@Override
	protected AnimalResource instantiateResource(Animal entity) {
		AnimalResource resource = new AnimalResource();
		resource.setName(entity.getName());
		return resource;
	}
}
