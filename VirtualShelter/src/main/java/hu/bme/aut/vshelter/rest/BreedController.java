package hu.bme.aut.vshelter.rest;

import hu.bme.aut.vshelter.api.ISiteAdministrationOperations;
import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.rest.resources.BreedResource;
import hu.bme.aut.vshelter.rest.resources.BreedResourceAssembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	/**
	 * Update the value of the breed
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	ResponseEntity<BreedResource> updateBreed(@PathVariable Long id) {
		//TODO
		return null;
	}
	
	/**
	 * Delete the breed
	 * 
	 * @param id
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	void deleteBreed(@PathVariable Long id) {
		try {
			this.siteAdministrationOperations.deleteBreed(id);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
		}
	}
}
