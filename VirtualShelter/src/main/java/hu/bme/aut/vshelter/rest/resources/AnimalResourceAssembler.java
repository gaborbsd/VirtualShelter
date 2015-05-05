package hu.bme.aut.vshelter.rest.resources;


import hu.bme.aut.vshelter.entity.Animal;
import hu.bme.aut.vshelter.rest.AnimalController;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.stereotype.Component;

/**
 * Converts Animal entities to AnimalResource objects
 * with links.
 * 
 * @author Kiss László
 *
 */
@Component
public class AnimalResourceAssembler extends
		ResourceAssemblerSupport<Animal, AnimalResource> {

	public AnimalResourceAssembler() {
		super(AnimalController.class, AnimalResource.class);
	}

	/**
	 * Public method for converting an Animal entity to AnimalResource object.
	 */
	@Override
	public AnimalResource toResource(Animal animal) {
		AnimalResource resource = createResourceWithId(animal.getId(), animal);
		return resource;
	}

	/**
	 * Create the resource object, set it's values and add links to it.
	 */
	@Override
	protected AnimalResource instantiateResource(Animal entity) {
		AnimalResource resource = new AnimalResource();
		resource.setName(entity.getName());
		resource.setAdoptionType(entity.getAdoptionType());
		resource.setBreed(entity.getBreed());
		resource.setHeight(entity.getHeight());
		resource.setWeight(entity.getWeight());
		resource.setAge(entity.getAge()!=null?entity.getAge().getTime().toString():null);
		resource.setSex(entity.getSex());
		resource.setSpayed(entity.isSpayed());
		resource.setVaccinationStatus(entity.getVaccinationStatus());
		resource.setDescription(entity.getDescription());
		resource.setKnownDiseases(entity.getKnownDiseases());
		resource.setKnownHandicaps(entity.getKnownHandicaps());
		resource.setAddress(entity.getAddress());
		
		resource.add(linkTo(AnimalController.class).slash(entity.getId()).slash("pictures").withRel("pictures"));
		resource.add(linkTo(AnimalController.class).slash(entity.getId()).slash("profile").withRel("profile"));
		resource.add(linkTo(AnimalController.class).slash(entity.getId()).slash("advertiser").withRel("advertiser"));
		return resource;
	}
	
}
