package hu.bme.aut.vshelter.rest;

import hu.bme.aut.vshelter.entity.Institution;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class InstitutionResourceAssembler extends ResourceAssemblerSupport<Institution, InstitutionResource>{

	public InstitutionResourceAssembler() {
		super(InstitutionController.class, InstitutionResource.class);
	}

	@Override
	public InstitutionResource toResource(Institution entity) {
		InstitutionResource resource = createResourceWithId(entity.getId(), entity);
		return resource;
	}
	
	protected InstitutionResource instantiateResource(Institution entity) {
		InstitutionResource resource = new InstitutionResource();
		resource.setName(entity.getName());
		resource.setAddress(entity.getAddress());
		resource.setTaxNumber(entity.getTaxNumber());
		resource.setBankAccount(entity.getBankAccount());
		resource.setPayPal(entity.getPayPal());
		return resource;
	}
}
