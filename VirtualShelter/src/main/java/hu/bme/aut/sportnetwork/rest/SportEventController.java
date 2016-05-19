package hu.bme.aut.sportnetwork.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.bme.aut.sportnetwork.dal.SportEventRepository;
import hu.bme.aut.sportnetwork.dal.UserRepository;
import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.entity.User;
import hu.bme.aut.sportnetwork.rest.resources.SportEventResource;
import hu.bme.aut.sportnetwork.rest.resources.SportEventResourceAssembler;
import hu.bme.aut.sportnetwork.rest.resources.UserResource;
import hu.bme.aut.sportnetwork.rest.resources.UserResourceAssembler;

@RestController
@RequestMapping(value="/sportevent")
public class SportEventController {
	
	@Autowired
	private SportEventRepository sportEventRepository;
	
	@Autowired
	private SportEventResourceAssembler sportEventResourceAssembler;
	
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<SportEventResource>> findAllSportEvent() {
		System.out.println("YEE");
		List<SportEvent> events = sportEventRepository.findAll();
		System.out.println(events.size());
		List<SportEventResource> resourceList = sportEventResourceAssembler
				.toResources(events);
		for (SportEventResource l : resourceList) {
			System.out.println(l.getType() + l.getOwner().getName());
		}
		return new ResponseEntity<List<SportEventResource>>(resourceList, HttpStatus.OK);
	}
	
	/*@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserResourceAssembler userResourceAssembler;
	
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<UserResource>> findAllUsers() {
		List<User> users = userRepository.findAll();
		
		List<UserResource> resourceList = userResourceAssembler
				.toResources(users);
		
		return new ResponseEntity<List<UserResource>>(resourceList, HttpStatus.OK);
	}*/

}
