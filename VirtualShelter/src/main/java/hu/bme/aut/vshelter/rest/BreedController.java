package hu.bme.aut.vshelter.rest;

import hu.bme.aut.vshelter.api.ISiteAdministrationOperations;
import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.entity.Breed;
import hu.bme.aut.vshelter.rest.resources.BreedResource;
import hu.bme.aut.vshelter.rest.resources.BreedResourceAssembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller class for the Breed resource.
 * 
 * @author Kiss László
 *
 */
@RestController
@RequestMapping(value="/breed")
public class BreedController {

	@Autowired
	private ISiteAdministrationOperations siteAdministrationOperations;
	
	@Autowired
	private BreedResourceAssembler breedResourceAssembler;
	
	private VirtualShelterExceptionToHttpStatusConverter converter = new VirtualShelterExceptionToHttpStatusConverter();
	
	/**
	 * Update the value of the breed
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	ResponseEntity<BreedResource> updateBreed(@PathVariable Long id, @RequestBody Breed breed) {
		HttpStatus responseStatus = HttpStatus.OK;
		breed.setId(id);
		try {
			this.siteAdministrationOperations.updateBreed(breed);
		} catch (VirtualShelterException e) {
			responseStatus = this.converter.convert(e);
		}
		BreedResource resource = this.breedResourceAssembler.toResource(breed);
		return new ResponseEntity<BreedResource>(resource, responseStatus);
	}
	
//	/**
//	 * Delete the breed
//	 * 
//	 * @param id
//	 */
//	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
//	ResponseEntity<BreedResource> deleteBreed(@PathVariable Long id) {
//		HttpStatus responseStatus = HttpStatus.OK;
//		try {
//			this.siteAdministrationOperations.deleteBreed(id);
//		} catch (VirtualShelterException e) {
//			responseStatus = this.converter.convert(e);
//		}
//		return new ResponseEntity<BreedResource>(responseStatus);
//	}
}
