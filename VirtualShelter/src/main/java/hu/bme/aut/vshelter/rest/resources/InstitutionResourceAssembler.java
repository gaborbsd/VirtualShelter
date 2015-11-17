package hu.bme.aut.vshelter.rest.resources;

import hu.bme.aut.vshelter.entity.Institution;
import hu.bme.aut.vshelter.rest.InstitutionController;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 *  Converts Institution entities to InstitutionResource objects
 *  with links.
 * 
 * @author Kiss László
 *
 */
@Component
public class InstitutionResourceAssembler extends ResourceAssemblerSupport<Institution, InstitutionResource>{

	public InstitutionResourceAssembler() {
		super(InstitutionController.class, InstitutionResource.class);
	}

	/**
	 * Public method for converting an Institution entity to InstitutionResource object.
	 */
	@Override
	public InstitutionResource toResource(Institution entity) {
		InstitutionResource resource = createResourceWithId(entity.getId(), entity);
		return resource;
	}
	
	/**
	 * Create the resource object, set it's values and add links to it.
	 */
	@Override
	protected InstitutionResource instantiateResource(Institution entity) {
		InstitutionResource resource = new InstitutionResource();
		resource.setInstitutionId(entity.getId());
		resource.setEmail(entity.getEmail());
		resource.setName(entity.getName());
		resource.setAddress(entity.getAddress());
		resource.setTaxNumber(entity.getTaxNumber());
		resource.setBankAccount(entity.getBankAccount());
		resource.setPayPal(entity.getPayPal());
		resource.setMobilePhoneNumber(entity.getMobilePhoneNumber());
		resource.setPhoneNumber(entity.getPhoneNumber());
		resource.setInstitutionAdministrators(entity.getInstitutionAdministrators());
		
		resource.add(linkTo(InstitutionController.class).slash(entity.getId()).slash("admin").withRel("admin"));
		resource.add(linkTo(InstitutionController.class).slash(entity.getId()).slash("advertisement").withRel("advertisement"));
		resource.add(linkTo(InstitutionController.class).slash(entity.getId()).slash("pictures").withRel("pictures"));
		resource.add(linkTo(InstitutionController.class).slash(entity.getId()).slash("profile").withRel("profile"));
		resource.add(linkTo(InstitutionController.class).slash(entity.getId()).slash("owner").withRel("owner"));
		
		return resource;
	}
}
