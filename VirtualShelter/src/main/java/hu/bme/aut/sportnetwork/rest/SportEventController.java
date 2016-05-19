package hu.bme.aut.sportnetwork.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.bme.aut.sportnetwork.api.ISportEventOperations;
import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.rest.resources.SportEventResource;
import hu.bme.aut.sportnetwork.rest.resources.SportEventResourceAssembler;

@RestController
@RequestMapping(value="/sportevent")
public class SportEventController {	
	
	@Autowired
	private ISportEventOperations sporteventOperation;
	
	@Autowired
	private SportEventResourceAssembler sportEventResourceAssembler;
	
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<SportEventResource>> findAllSportEvent() {
		List<SportEvent> events = sporteventOperation.findAll();
		
		List<SportEventResource> resourceList = sportEventResourceAssembler
				.toResources(events);
		
		return new ResponseEntity<List<SportEventResource>>(resourceList, HttpStatus.OK);
	}

}
