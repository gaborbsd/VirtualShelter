package hu.bme.aut.vshelter.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/picture")
public class PictureController {

	/**
	 * Find one picture by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<PictureResource> getPicture(@PathVariable Long id) {
		//TODO
		return null;
	}
	
	/**
	 * Delete the picture
	 * 
	 * @param id
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	void deletePicture(@PathVariable Long id) {
		//TODO
	}
	
}
