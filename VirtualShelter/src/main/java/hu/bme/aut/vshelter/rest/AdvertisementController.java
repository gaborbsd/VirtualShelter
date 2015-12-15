package hu.bme.aut.vshelter.rest;

import hu.bme.aut.vshelter.api.IAdvertisementOperations;
import hu.bme.aut.vshelter.api.ISiteAdministrationOperations;
import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.entity.Advertisement;
import hu.bme.aut.vshelter.entity.Advertiser;
import hu.bme.aut.vshelter.entity.Animal;
import hu.bme.aut.vshelter.entity.User;
import hu.bme.aut.vshelter.rest.resources.AdvertisementResource;
import hu.bme.aut.vshelter.rest.resources.AdvertisementResourceAssembler;
import hu.bme.aut.vshelter.rest.resources.AnimalResource;
import hu.bme.aut.vshelter.rest.resources.AnimalResourceAssembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Rest controller class for the Animal resource
 * 
 * @author badam
 *
 */

@RestController
@RequestMapping(value = "/advertisement")
public class AdvertisementController {
	
	@Autowired
	private IAdvertisementOperations advertisementOperations;
	
	@Autowired
	private AdvertisementResourceAssembler advertisementResourceAssembler;
	
	@Autowired
	private ISiteAdministrationOperations siteAdministrationOperations;
	
	@Autowired
	private AnimalResourceAssembler animalResourceAssembler;
	
	private VirtualShelterExceptionToHttpStatusConverter converter = new VirtualShelterExceptionToHttpStatusConverter();

	/**
	 * List all advertisement
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<AdvertisementResource>> getAllAdvertisement() {
		List<Advertisement> advertisements;
		List<AdvertisementResource> resourceList = new ArrayList<AdvertisementResource>();
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			advertisements = advertisementOperations.listAllAdvertisements();
			advertisements.stream().forEach(a -> resourceList.add(advertisementResourceAssembler.toResource(a)));
		} catch (VirtualShelterException e) {
			responseStatus = converter.convert(e);
		}
		return new ResponseEntity<List<AdvertisementResource>>(resourceList ,responseStatus);
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<AdvertisementResource> addAdvertisement(Principal principal, @RequestBody Advertisement adv) {
			HttpStatus responseStatus = HttpStatus.CREATED;
			String userMail = principal.getName();
			Advertiser adver = null;
			try {
				adver = siteAdministrationOperations.findUserByEmail(userMail);
				adv.setAdvertiser(adver);
				this.advertisementOperations.createAdvertisement(adv);;
			} catch (VirtualShelterException e) {
				responseStatus = this.converter.convert(e);
			}
		AdvertisementResource resource = advertisementResourceAssembler.toResource(adv);
		return new ResponseEntity<AdvertisementResource>(resource, responseStatus);
	}
	
	
	/**
	 * Find one advertisement by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	ResponseEntity<AdvertisementResource> getAdvertisement(@PathVariable Long id) {
		HttpStatus responseStatus = HttpStatus.OK;
		AdvertisementResource resource = null;
		try {
			Advertisement a = advertisementOperations.getAdvertisement(id);
			resource = advertisementResourceAssembler.toResource(a);
		} catch (VirtualShelterException e) {
			responseStatus = converter.convert(e);
		}
		return new ResponseEntity<AdvertisementResource>(resource, responseStatus);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	ResponseEntity<AdvertisementResource> deleteAdvertisement(@PathVariable Long id) {
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			siteAdministrationOperations.deleteAdvertisement(id);
		} catch (VirtualShelterException e) {
			responseStatus = converter.convert(e);
		}
		return new ResponseEntity<AdvertisementResource>(responseStatus);
	}
	
	@RequestMapping(value="/animals", method = RequestMethod.GET)
	@ResponseBody
	ResponseEntity<List<AnimalResource>> getAnimalsForCurrentUser(Principal principal) {
		HttpStatus responseStatus = HttpStatus.OK;
		String userName = principal.getName();
		List<AnimalResource> resourceList = new ArrayList<AnimalResource>();
		try {
			User u = siteAdministrationOperations.findUserByEmail(userName);
			List<Animal> animals = advertisementOperations.listAnimalsAdvertisedByAdvertiser(u.getId());
			for (Animal animal : animals) {
				resourceList.add(this.animalResourceAssembler.toResource(animal));
			}
		} catch (VirtualShelterException e) {
			responseStatus = converter.convert(e);
		}
		return new ResponseEntity<List<AnimalResource>>(resourceList, responseStatus);
	}
	
}
