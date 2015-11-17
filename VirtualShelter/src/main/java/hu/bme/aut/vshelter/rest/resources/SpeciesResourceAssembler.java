package hu.bme.aut.vshelter.rest.resources;

import hu.bme.aut.vshelter.entity.Species;
import hu.bme.aut.vshelter.rest.SpeciesController;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 *  Converts Species entities to SpeciesResource objects
 *  with links.
 * 
 * @author Kiss László
 *
 */
@Component
public class SpeciesResourceAssembler extends ResourceAssemblerSupport<Species, SpeciesResource> {

	public SpeciesResourceAssembler() {
		super(SpeciesController.class, SpeciesResource.class);
	}

	/**
	 * Public method for converting an Institution entity to InstitutionResource object.
	 */
	@Override
	public SpeciesResource toResource(Species entity) {
		SpeciesResource resource = createResourceWithId(entity.getId(), entity);
		return resource;
	}
	
	/**
	 * Create the resource object, set it's values and add links to it.
	 */
	@Override
	protected SpeciesResource instantiateResource(Species entity) {
		SpeciesResource resource = new SpeciesResource();
		resource.setName(entity.getSpeciesName());
		resource.setBreeds(entity.getBreeds());
		
		//resource.add(linkTo(SpeciesController.class).slash(entity.getId()).slash("breed").withRel("breed"));
		
		return resource;
	}
	
}
