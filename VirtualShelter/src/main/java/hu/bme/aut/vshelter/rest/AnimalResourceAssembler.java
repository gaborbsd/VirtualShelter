package hu.bme.aut.vshelter.rest;

import hu.bme.aut.vshelter.entity.Animal;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

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
