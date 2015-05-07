package hu.bme.aut.vshelter.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import hu.bme.aut.vshelter.api.IAdvertisementOperations;
import hu.bme.aut.vshelter.api.ISiteAdministrationOperations;
import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.entity.Breed;
import hu.bme.aut.vshelter.entity.Species;
import hu.bme.aut.vshelter.rest.resources.BreedResource;
import hu.bme.aut.vshelter.rest.resources.BreedResourceAssembler;
import hu.bme.aut.vshelter.rest.resources.SpeciesResource;
import hu.bme.aut.vshelter.rest.resources.SpeciesResourceAssembler;

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
	private BreedResourceAssembler breedResourceAssembler;
	
	@Autowired
	private ISiteAdministrationOperations siteAdministrationOperations;
	
	@Autowired
	private IAdvertisementOperations advertisementOperations;
	
	private VirtualShelterExceptionToHttpStatusConverter converter = new VirtualShelterExceptionToHttpStatusConverter();
	
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
			resources.addAll(this.speciesResourceAssembler.toResources(speciesList));
		} catch (VirtualShelterException e) {
			responseStatus = converter.convert(e);
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
		HttpStatus responseStatus = HttpStatus.CREATED;
		try {
			this.siteAdministrationOperations.addSpecies(species.getSpeciesName());
		} catch (VirtualShelterException e) {
			responseStatus = converter.convert(e);
		}
		SpeciesResource resource = this.speciesResourceAssembler.toResource(species);
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
		SpeciesResource resource = null;
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			Species species = this.advertisementOperations.findSpeciesById(id);
			resource = this.speciesResourceAssembler.toResource(species);
		} catch (VirtualShelterException e) {
			responseStatus = converter.convert(e);
		}
		return new ResponseEntity<SpeciesResource>(resource, responseStatus);
	}
	
	/**
	 * Update the species identified by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	ResponseEntity<SpeciesResource> updateSpecies(@PathVariable Long id, @RequestBody Species species) {
		HttpStatus responseStatus = HttpStatus.OK;
		species.setId(id);
		try {
			this.advertisementOperations.updateSpecies(species);
		} catch (VirtualShelterException e) {
			responseStatus = converter.convert(e);
		}
		SpeciesResource resource = this.speciesResourceAssembler.toResource(species);
		return new ResponseEntity<SpeciesResource>(resource, responseStatus);
	}
	
	/**
	 * Delete one species
	 * 
	 * @param id
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	ResponseEntity<SpeciesResource> deleteSpecies(@PathVariable Long id) {
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			this.siteAdministrationOperations.deleteSpecies(id);
		} catch (VirtualShelterException e) {
			responseStatus = converter.convert(e);
		}
		return new ResponseEntity<SpeciesResource>(responseStatus);
	}
	
	/**
	 * List all breeds of the species
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/breed", method=RequestMethod.GET)
	ResponseEntity<List<BreedResource>> findAllBreedOfSpecies(@PathVariable Long id) {
		HttpStatus responseStatus = HttpStatus.OK;
		List<BreedResource> resources = new ArrayList<BreedResource>();
		try {
			Set<Breed> breedList = this.advertisementOperations.listBreedsOfTheSpecies(id);
			resources.addAll(this.breedResourceAssembler.toResources(breedList));
		} catch (VirtualShelterException e) {
			responseStatus = converter.convert(e);
		}
		return new ResponseEntity<List<BreedResource>>(resources, responseStatus);
	}
	
	/**
	 * Post new breed of the species
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/breed", method=RequestMethod.POST)
	ResponseEntity<BreedResource> createNewBreed(@PathVariable Long id, @RequestBody Breed breed) {
		HttpStatus responseStatus = HttpStatus.CREATED;
		try {
			this.siteAdministrationOperations.addBreed(breed.getBreedName(), id);
		} catch (VirtualShelterException e) {
			responseStatus = converter.convert(e);
		}
		BreedResource breedResource = this.breedResourceAssembler.toResource(breed);
		return new ResponseEntity<BreedResource>(breedResource, responseStatus);
	}
}
