package hu.bme.aut.vshelter.rest.resources;

import hu.bme.aut.vshelter.entity.Picture;
import hu.bme.aut.vshelter.rest.PictureController;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class PictureResourceAssembler extends ResourceAssemblerSupport<Picture, PictureResource> {

	public PictureResourceAssembler() {
		super(PictureController.class, PictureResource.class);
	}

	@Override
	public PictureResource toResource(Picture entity) {
		PictureResource resource = createResourceWithId(entity.getId(), entity);
		return resource;
	}
	
	@Override
	protected PictureResource instantiateResource(Picture entity) {
		PictureResource resource = new PictureResource();
		
		//TODO
		
		return resource;
	}
	
}
