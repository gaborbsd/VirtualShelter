package hu.bme.aut.vshelter.rest;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.vshelter.api.IAdvertisementOperations;
import hu.bme.aut.vshelter.api.ISiteAdministrationOperations;
import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.entity.Species;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller class for the Species resource.
 * 
 * @author Kiss László
 *
 */
@RestController
@RequestMapping(value="/species")
public class SpeciesController {

	@Autowired
	private SpeciesResourceAssembler speciesResourceAssembler;
	
	@Autowired
	private ISiteAdministrationOperations siteAdministrationOperations;
	
	@Autowired
	private IAdvertisementOperations advertisementOperations;
	
	/**
	 * List all species
	 * 
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<SpeciesResource>> getAllSpecies() {
		List<Species> speciesList = new ArrayList<>();
		List<SpeciesResource> resources = new ArrayList<SpeciesResource>();
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			speciesList = this.advertisementOperations.listAllSpecies();
			for (Species species : speciesList) {
				SpeciesResource resource = this.speciesResourceAssembler.toResource(species);
				resource.add(linkTo(SpeciesController.class).slash(species.getId()).slash("breed").withRel("Breed"));
				resources.add(resource);
			}
		} catch (VirtualShelterException e) {
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<SpeciesResource>>(resources, responseStatus);
	}
	
	/**
	 * Create new species
	 * 
	 * @param species
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	ResponseEntity<SpeciesResource> addSpecies(@RequestBody Species species) {
		SpeciesResource resource = this.speciesResourceAssembler.toResource(species);
		HttpStatus responseStatus = HttpStatus.CREATED;
		try {
			this.siteAdministrationOperations.addSpecies(species.getSpeciesName());
		} catch (VirtualShelterException e) {
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<SpeciesResource>(resource, responseStatus);
	}
	
	/**
	 * Find one species by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<SpeciesResource> getSpecies(@PathVariable Long id) {
		//TODO
		return null;
	}
	
	/**
	 * Update the species identified by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	ResponseEntity<SpeciesResource> updateSpecies(@PathVariable Long id) {
		//TODO
		return null;
	}
	
	/**
	 * Delete one species
	 * 
	 * @param id
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	void deleteSpecies(@PathVariable Long id) {
		//TODO
	}
	
	/**
	 * List all breeds of the species
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<List<BreedResource>> findAllBreedOfSpecies(@PathVariable Long id) {
		//TODO
		return null;
	}
	
	/**
	 * Post new breed of the specied
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	ResponseEntity<BreedResource> createNewBreed(@PathVariable Long id) {
		//TODO
		return null;
	}
}
