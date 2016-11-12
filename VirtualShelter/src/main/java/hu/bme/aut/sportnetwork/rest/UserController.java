package hu.bme.aut.sportnetwork.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.bme.aut.sportnetwork.api.RegistrationOperations;
import hu.bme.aut.sportnetwork.api.UserOperations;
import hu.bme.aut.sportnetwork.entity.User;
import hu.bme.aut.sportnetwork.rest.resources.SportEventResource;
import hu.bme.aut.sportnetwork.rest.resources.UserResource;
import hu.bme.aut.sportnetwork.rest.resources.UserResourceAssembler;

@RestController
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	RegistrationOperations registrationOperation;
	
	@Autowired
	UserOperations userOperation; 
	
	@Autowired
	UserResourceAssembler userResourceAssembler;
	
	/*@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<UserResource>> findAllUsers() {
		List<User> users = userRepository.findAll();
		
		List<UserResource> resourceList = userResourceAssembler
				.toResources(users);
		
		return new ResponseEntity<List<UserResource>>(resourceList, HttpStatus.OK);
	}
	*/
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<UserResource> getUser(@PathVariable Long id) {
		User user = userOperation.findById(id);
		UserResource resource = userResourceAssembler.toResource(user);
		return new ResponseEntity<UserResource>(resource, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	ResponseEntity<UserResource> addUser(@RequestBody User user) {
		/*Address a = new Address();
		a.setCity("KAp");
		a.setCountry("HUN");
		a.setZipCode(7400);
		a.setStreet("Honv");
		User user = new User();
		user.setEmail("denes4@gmail.com");
		user.setName("Denes");
		user.setPassword("d");
		user.setAddress(a);*/
		User created = registrationOperation.registrate(user);
		UserResource resource = userResourceAssembler.toResource(created);
		return new ResponseEntity<UserResource>(resource, HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<UserResource> getCurrentUser(Principal principal) {
		User user = userOperation.findByName(principal.getName());
		UserResource resource = userResourceAssembler.toResource(user);
		return new ResponseEntity<UserResource>(resource, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="friends", method=RequestMethod.GET)
	ResponseEntity<List<UserResource>> listFriends() {
		List<User> friends = userOperation.listFriends();
		List<UserResource> resourceList = userResourceAssembler.toResources(friends);
		return new ResponseEntity<List<UserResource>>(resourceList, HttpStatus.OK);
	}
	
	@RequestMapping(value="friendRequests", method=RequestMethod.GET)
	ResponseEntity<List<UserResource>> listFriendRequests() {
		List<User> friends = userOperation.listFriendRequest();
		List<UserResource> resourceList = userResourceAssembler.toResources(friends);
		return new ResponseEntity<List<UserResource>>(resourceList, HttpStatus.OK);
	}
}
