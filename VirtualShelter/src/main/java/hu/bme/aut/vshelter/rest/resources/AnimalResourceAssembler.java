package hu.bme.aut.vshelter.rest.resources;


import hu.bme.aut.vshelter.entity.Animal;
import hu.bme.aut.vshelter.rest.AnimalController;

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
		resource.setAdoptionType(entity.getAdoptionType());
//		resource.setBreed(entity.getBreed()!=null?entity.getBreed():null);
		resource.setHeight(entity.getHeight());
		resource.setWeight(entity.getWeight());
		resource.setAge(entity.getAge()!=null?entity.getAge().getTime().toString():null);
		resource.setSex(entity.getSex());
		resource.setSpayed(entity.isSpayed());
		resource.setVaccinationStatus(entity.getVaccinationStatus());
		resource.setDescription(entity.getDescription());
//		resource.setKnownDiseases(entity.getKnownDiseases());
//		resource.setKnownHandicaps(entity.getKnownHandicaps());
		resource.setAddress(entity.getAddress());
		return resource;
	}
	
}
