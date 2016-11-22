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
import hu.bme.aut.sportnetwork.rest.resources.FilterSportEventArg;
import hu.bme.aut.sportnetwork.rest.resources.SportEventResource;
import hu.bme.aut.sportnetwork.rest.resources.SportEventResourceAssembler;
import hu.bme.aut.sportnetwork.rest.resources.SportEventShortResource;
import hu.bme.aut.sportnetwork.rest.resources.SportEventShortResourceAssembler;
import hu.bme.aut.sportnetwork.rest.resources.UserResource;
import hu.bme.aut.sportnetwork.rest.resources.WriteCommentArg;

@RestController
@RequestMapping(value="/sportevent")
public class SportEventController {	
	
	@Autowired
	private SportEventOperations sporteventOperation;
	
	@Autowired
	UserOperations userOperation; 
	
	@Autowired
	private SportEventResourceAssembler sportEventResourceAssembler;
	
	@Autowired
	private SportEventShortResourceAssembler sportEventShortResourceAssembler;
	
	@RequestMapping(value="public", method=RequestMethod.GET)
	ResponseEntity<List<SportEventShortResource>> findAllSportEvent() {
		List<SportEvent> events = sporteventOperation.findAllOpenedEvents();
		List<SportEventShortResource> resourceList = sportEventShortResourceAssembler
				.toResources(events);
		return new ResponseEntity<List<SportEventShortResource>>(resourceList, HttpStatus.OK);
	}
	
	@RequestMapping(value="public/search", method=RequestMethod.POST)
	ResponseEntity<List<SportEventShortResource>> findFilteredSportEvent(@RequestBody FilterSportEventArg arg) throws Exception {
		List<SportEvent> events = sporteventOperation.filterPublicEvents(arg);
		List<SportEventShortResource> resourceList = sportEventShortResourceAssembler
				.toResources(events);
		return new ResponseEntity<List<SportEventShortResource>>(resourceList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	ResponseEntity<SportEventResource> createEvent(/*Principal principal ,*/@RequestBody SportEvent event) {
		/*User owner = userOperation.findByName(principal.getName());
		event.setOwner(owner);*/
		SportEvent created = sporteventOperation.create(event);
		SportEventResource resource = sportEventResourceAssembler.toResource(created);
		return new ResponseEntity<SportEventResource>(resource, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="write", method=RequestMethod.POST)
	ResponseEntity<SportEventResource> writeComment(@RequestBody WriteCommentArg arg) {
		SportEvent event = sporteventOperation.writeComment(arg.getEventId(), arg.getMessage());
		SportEventResource resource = sportEventResourceAssembler.toResource(event);
		return new ResponseEntity<SportEventResource>(resource, HttpStatus.OK);
	}
	
	@RequestMapping(value="/delcomment/{id}", method=RequestMethod.DELETE)
	ResponseEntity<SportEventResource> deleteComment(@PathVariable Long id) {
		SportEvent event = sporteventOperation.deleteComment(id);
		SportEventResource resource = sportEventResourceAssembler.toResource(event);
		return new ResponseEntity<SportEventResource>(resource, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<SportEventResource> getSportEvent(@PathVariable Long id) {
		SportEvent event = sporteventOperation.findById(id);
		SportEventResource resource = sportEventResourceAssembler.toResource(event);
		return new ResponseEntity<SportEventResource>(resource, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/apply/{id}", method=RequestMethod.POST)
	ResponseEntity<SportEventResource> applyEvent(@PathVariable Long id) throws Exception {
		SportEvent event = sporteventOperation.applyToSportEvent(id);		
		SportEventResource resource = sportEventResourceAssembler.toResource(event);
		return new ResponseEntity<SportEventResource>(resource, HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	ResponseEntity<String> deleteEvent(@PathVariable Long id) throws Exception {
		sporteventOperation.deleteEvent(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/close/{id}", method=RequestMethod.PUT)
	ResponseEntity<SportEventResource> closeEvent(@PathVariable Long id) throws Exception {
		SportEvent event = sporteventOperation.closeEvent(id);
		SportEventResource resource = sportEventResourceAssembler.toResource(event);
		return new ResponseEntity<SportEventResource>(resource, HttpStatus.OK);
	}
	
	@RequestMapping(value="/cancel/{id}", method=RequestMethod.DELETE)
	ResponseEntity<SportEventResource> cancelEventRequest(@PathVariable Long id) throws Exception {
		SportEvent event = sporteventOperation.cancelEventRequest(id);
		SportEventResource resource = sportEventResourceAssembler.toResource(event);
		return new ResponseEntity<SportEventResource>(resource, HttpStatus.OK);
	}
	
	@RequestMapping(value="/leave/{id}", method=RequestMethod.DELETE)
	ResponseEntity<SportEventResource> leaveEvent(@PathVariable Long id) throws Exception {
		SportEvent event = sporteventOperation.removeUserFromSportEvent(id);
		SportEventResource resource = sportEventResourceAssembler.toResource(event);
		return new ResponseEntity<SportEventResource>(resource, HttpStatus.OK);
	}
}
