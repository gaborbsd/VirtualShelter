package hu.bme.aut.vshelter.rest;

import hu.bme.aut.vshelter.api.IAdvertisementOperations;
import hu.bme.aut.vshelter.api.ISiteAdministrationOperations;
import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.entity.Advertisement;
import hu.bme.aut.vshelter.entity.Advertiser;
import hu.bme.aut.vshelter.entity.Animal;
import hu.bme.aut.vshelter.entity.Institution;
import hu.bme.aut.vshelter.entity.Picture;
import hu.bme.aut.vshelter.entity.User;
import hu.bme.aut.vshelter.rest.resources.AnimalResource;
import hu.bme.aut.vshelter.rest.resources.AnimalResourceAssembler;
import hu.bme.aut.vshelter.rest.resources.InstitutionResource;
import hu.bme.aut.vshelter.rest.resources.InstitutionResourceAssembler;
import hu.bme.aut.vshelter.rest.resources.PictureResource;
import hu.bme.aut.vshelter.rest.resources.UserResource;
import hu.bme.aut.vshelter.rest.resources.UserResourceAssembler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller class for the Animal resource
 * 
 * @author Kiss László
 *
 */
@RestController
@RequestMapping(value = "/animal")
public class AnimalController {
	
	@Autowired
	private ISiteAdministrationOperations siteAdministrationOperations;
	
	@Autowired
	private IAdvertisementOperations advertisementOperations;
	
	@Autowired
	private AnimalResourceAssembler animalResourceAssembler;

	@Autowired
	private UserResourceAssembler userResourceAssembler;
	
	@Autowired
	private InstitutionResourceAssembler institutionResourceAssembler;
	
	private VirtualShelterExceptionToHttpStatusConverter converter = new VirtualShelterExceptionToHttpStatusConverter();
	 
	
	/**
	 * Register new animal
	 * 
	 * @param animal
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<AnimalResource> addAnimal(@RequestBody Animal animal) {
		HttpStatus responseStatus = HttpStatus.CREATED;
		try {
			this.advertisementOperations.addAnimal(animal);
		} catch (VirtualShelterException e) {
			responseStatus = this.converter.convert(e);
		}
		
		AnimalResource resource = animalResourceAssembler.toResource(animal);
		return new ResponseEntity<AnimalResource>(resource, responseStatus);
	}

	/**
	 * List all animals
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<AnimalResource>> findAllAnimals() {
		List<Animal> animals ;
		List<AnimalResource> resourceList = new ArrayList<AnimalResource>();
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			animals = this.advertisementOperations.getAnimals();
			for (Animal animal : animals) {
				resourceList.add(this.animalResourceAssembler.toResource(animal));
			}
		} catch (VirtualShelterException e) {
			responseStatus = this.converter.convert(e);
		}
		
		return new ResponseEntity<List<AnimalResource>>(resourceList,
				responseStatus);
	}
	
	/**
	 * Find one animal by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<AnimalResource> getAnimal(@PathVariable Long id) {
		HttpStatus responseStatus = HttpStatus.OK;
		AnimalResource resource = null;
		try {
			Animal animal = this.advertisementOperations.findAnimalById(id);
			resource = this.animalResourceAssembler.toResource(animal);
			} catch (VirtualShelterException e) {
				responseStatus = this.converter.convert(e);
			}
		return new ResponseEntity<AnimalResource>(resource, responseStatus);
	}
	
	/**
	 * Updates the animal
	 * 
	 * @param id
	 * @param animal
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	ResponseEntity<AnimalResource> updateAnimal(@PathVariable Long id, @RequestBody Animal animal) {
		HttpStatus responseStatus = HttpStatus.OK;
		animal.setId(id);
		try {
			this.advertisementOperations.updateAnimal(animal);
		} catch (VirtualShelterException e) {
			responseStatus = this.converter.convert(e);
		}
		AnimalResource resource = this.animalResourceAssembler.toResource(animal);
		return new ResponseEntity<AnimalResource>(resource, responseStatus);
	}
	
	/**
	 * Delete the animal
	 * 
	 * @param id
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	ResponseEntity<AnimalResource> deleteAnimal(@PathVariable Long id) {
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			this.advertisementOperations.deleteAnimal(id);
		} catch (VirtualShelterException e) {
			responseStatus = this.converter.convert(e);
		}
		return new ResponseEntity<AnimalResource>(responseStatus);
	}
	
	/**
	 * List the pictures of the animal
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/pictures", method=RequestMethod.GET)
	ResponseEntity<List<PictureResource>> getAnimalPictures(@PathVariable Long id) {
		//TODO
		return null;
	}
	
	/**
	 * Post new picture of the animal
	 * 
	 * @param id
	 * @param picture
	 * @return
	 */
	@RequestMapping(value="/{id}/pictures", method=RequestMethod.POST)
	ResponseEntity<PictureResource> createAnimalPicture(@PathVariable Long id, @RequestBody Picture picture) {
		//TODO
		return null;
	}
	
	/**
	 * Returns the profile picture of the animal
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/profile", method=RequestMethod.GET)
	ResponseEntity<PictureResource> getAnimalProfilePicture(@PathVariable Long id) {
		//TODO
		return null;
	}
	
	/**
	 * Post new profile picture for the animal
	 * 
	 * @param id
	 * @param picture
	 * @return
	 */
	@RequestMapping(value="/{id}/profile", method=RequestMethod.POST)
	ResponseEntity<PictureResource> createAnimalProfilePicture(@PathVariable Long id, @RequestBody Picture picture) {
		//TODO
		return null;
	}
	
	/**
	 * Delete the profile picture of the animal
	 * 
	 * @param id
	 */
	@RequestMapping(value="/{id}/profile", method=RequestMethod.DELETE)
	void deleteAnimalProfilePicture(@PathVariable Long id) {
		//TODO
	}
	
	/**
	 * Returns the advertiser of the animal
	 * (institution or user?)
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/advertiser", method=RequestMethod.GET)
	ResponseEntity<ResourceSupport> getAdvertiser(@PathVariable Long id) {
		HttpStatus responseStatus = HttpStatus.OK;
		UserResource userResource = null;
		InstitutionResource institutionResource = null;
		boolean isUser = false;
		try {
			Advertiser advertiser = this.advertisementOperations.getAdvertiserOfAnimal(id);
			if (advertiser instanceof User) {
				userResource = this.userResourceAssembler.toResource((User)advertiser);
				isUser = true;
			} else {
				institutionResource = this.institutionResourceAssembler.toResource((Institution) advertiser);
			}
		} catch (VirtualShelterException e) {
			responseStatus = this.converter.convert(e);
		}
		return new ResponseEntity<ResourceSupport>(isUser?userResource:institutionResource, responseStatus);
		 	
	}
	
}
