package hu.bme.aut.vshelter.rest;

import hu.bme.aut.vshelter.api.IAdvertisementOperations;
import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.entity.Advertisement;
import hu.bme.aut.vshelter.rest.resources.AdvertisementResource;
import hu.bme.aut.vshelter.rest.resources.AdvertisementResourceAssembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
