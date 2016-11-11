package hu.bme.aut.sportnetwork.rest;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.bme.aut.sportnetwork.api.SportEventOperations;
import hu.bme.aut.sportnetwork.api.UserOperations;
import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.entity.User;
import hu.bme.aut.sportnetwork.rest.resources.SportEventResource;
import hu.bme.aut.sportnetwork.rest.resources.SportEventResourceAssembler;
import hu.bme.aut.sportnetwork.rest.resources.UserResource;

@RestController
@RequestMapping(value="/sportevent")
public class SportEventController {	
	
	@Autowired
	private SportEventOperations sporteventOperation;
	
	@Autowired
	UserOperations userOperation; 
	
	@Autowired
	private SportEventResourceAssembler sportEventResourceAssembler;
	
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<SportEventResource>> findAllSportEvent() {
		List<SportEvent> events = sporteventOperation.findAll();
		List<SportEventResource> resourceList = sportEventResourceAssembler
				.toResources(events);
		return new ResponseEntity<List<SportEventResource>>(resourceList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	ResponseEntity<SportEventResource> createEvent(/*Principal principal ,*/@RequestBody SportEvent event) {
		/*User owner = userOperation.findByName(principal.getName());
		event.setOwner(owner);*/
		event.setDate(new Date());
		SportEvent created = sporteventOperation.create(event);
		SportEventResource resource = sportEventResourceAssembler.toResource(created);
		return new ResponseEntity<SportEventResource>(resource, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<SportEventResource> getSportEvent(@PathVariable Long id) {
		SportEvent event = sporteventOperation.findById(id);
		SportEventResource resource = sportEventResourceAssembler.toResource(event);
		return new ResponseEntity<SportEventResource>(resource, HttpStatus.CREATED);
	}

}
