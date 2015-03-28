package hu.bme.aut.vshelter.rest;

import hu.bme.aut.vshelter.entity.Species;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class SpeciesResourceAssembler extends ResourceAssemblerSupport<Species, SpeciesResource> {

	public SpeciesResourceAssembler() {
		super(SpeciesController.class, SpeciesResource.class);
	}

	@Override
	public SpeciesResource toResource(Species entity) {
		SpeciesResource resource = createResourceWithId(entity.getId(), entity);
		return resource;
	}
	
	@Override
	protected SpeciesResource instantiateResource(Species entity) {
		SpeciesResource resource = new SpeciesResource();
		resource.setName(entity.getSpeciesName());
		
		return resource;
	}
	
}
