package hu.bme.aut.sportnetwork.rest;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.bme.aut.sportnetwork.api.ISportEventOperations;
import hu.bme.aut.sportnetwork.api.IUserOperation;
import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.entity.User;
import hu.bme.aut.sportnetwork.rest.resources.SportEventResource;
import hu.bme.aut.sportnetwork.rest.resources.SportEventResourceAssembler;

@RestController
@RequestMapping(value="/sportevent")
public class SportEventController {	
	
	@Autowired
	private ISportEventOperations sporteventOperation;
	
	@Autowired
	IUserOperation userOperation; 
	
	@Autowired
	private SportEventResourceAssembler sportEventResourceAssembler;
	
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<SportEventResource>> findAllSportEvent() {
		System.out.println("FINDALLEVENT");
		List<SportEvent> events = sporteventOperation.findAll();
		System.out.println("events" + events.size());
		List<SportEventResource> resourceList = sportEventResourceAssembler
				.toResources(events);
		System.out.println("Succes" + resourceList);
		return new ResponseEntity<List<SportEventResource>>(resourceList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	ResponseEntity<SportEventResource> createEvent(Principal principal ,@RequestBody SportEvent event) {
		User owner = userOperation.findByName(principal.getName());
		event.setOwner(owner);
		event.setDate(new Date());
		SportEvent created = sporteventOperation.create(event);
		SportEventResource resource = sportEventResourceAssembler.toResource(created);
		return new ResponseEntity<SportEventResource>(resource, HttpStatus.CREATED);
	}

}
