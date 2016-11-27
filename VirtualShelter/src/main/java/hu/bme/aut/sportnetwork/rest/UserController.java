package hu.bme.aut.sportnetwork.rest;

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
import hu.bme.aut.sportnetwork.rest.resources.FriendRequestArg;
import hu.bme.aut.sportnetwork.rest.resources.StringArg;
import hu.bme.aut.sportnetwork.rest.resources.UserArg;
import hu.bme.aut.sportnetwork.rest.resources.UserResource;
import hu.bme.aut.sportnetwork.rest.resources.UserResourceAssembler;
import hu.bme.aut.sportnetwork.rest.resources.UserShortResource;
import hu.bme.aut.sportnetwork.rest.resources.UserShortResourceAssembler;

@RestController
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	RegistrationOperations registrationOperation;
	
	@Autowired
	UserOperations userOperation; 
	
	@Autowired
	UserResourceAssembler userResourceAssembler;
	
	@Autowired
	UserShortResourceAssembler userShortResourceAssembler;

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
	ResponseEntity<String> addUser(@RequestBody UserArg user) {
		registrationOperation.registrate(user);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<UserResource> getCurrentUser() {
		User user = userOperation.getCurrent();
		UserResource resource = userResourceAssembler.toResource(user);
		return new ResponseEntity<UserResource>(resource, HttpStatus.OK);
	}
	
	@RequestMapping(value="friends", method=RequestMethod.GET)
	ResponseEntity<List<UserShortResource>> listFriends() {
		List<User> friends = userOperation.listFriends();
		List<UserShortResource> resourceList = userShortResourceAssembler.toResources(friends);
		return new ResponseEntity<List<UserShortResource>>(resourceList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "friend", method = RequestMethod.POST)
	ResponseEntity<UserResource> sendFriendRequest(@RequestBody FriendRequestArg arg) {
		User user = userOperation.sendFriendRequest(arg.getTo());
		UserResource resource = userResourceAssembler.toResource(user);
		return new ResponseEntity<UserResource>(resource, HttpStatus.OK);
	}

	@RequestMapping(value = "cancel", method = RequestMethod.DELETE)
	ResponseEntity<UserResource> cancelFriendRequest(@RequestBody FriendRequestArg arg) throws Exception {
		User user = userOperation.cancelFriendRequest(arg.getTo());
		UserResource resource = userResourceAssembler.toResource(user);
		return new ResponseEntity<UserResource>(resource, HttpStatus.OK);
	}

	@RequestMapping(value = "decline", method = RequestMethod.PUT)
	ResponseEntity<UserResource> declineFriendRequest(@RequestBody FriendRequestArg arg) throws Exception {
		User user = userOperation.declineFriendRequest(arg.getTo());
		UserResource resource = userResourceAssembler.toResource(user);
		return new ResponseEntity<UserResource>(resource, HttpStatus.OK);
	}

	@RequestMapping(value = "deleteFriend/{id}", method = RequestMethod.DELETE)
	ResponseEntity<UserResource> deleteFriend(@PathVariable Long id) {
		User user = userOperation.deleteFriend(id);
		UserResource resource = userResourceAssembler.toResource(user);
		return new ResponseEntity<UserResource>(resource, HttpStatus.OK);
	}

	@RequestMapping(value="friendRequests", method=RequestMethod.GET)
	ResponseEntity<List<UserResource>> listFriendRequests() {
		List<User> friends = userOperation.listFriendRequest();
		List<UserResource> resourceList = userResourceAssembler.toResources(friends);
		return new ResponseEntity<List<UserResource>>(resourceList, HttpStatus.OK);
	}

	@RequestMapping(value = "search", method = RequestMethod.POST)
	ResponseEntity<List<UserShortResource>> searchUser(@RequestBody StringArg arg) {
		List<User> friends = userOperation.search(arg.getValue());
		List<UserShortResource> resourceList = userShortResourceAssembler.toResources(friends);
		return new ResponseEntity<List<UserShortResource>>(resourceList, HttpStatus.OK);
	}

	@RequestMapping(value = "friendAccept/{id}", method = RequestMethod.PUT)
	ResponseEntity<UserResource> acceptFriendRequest(@PathVariable Long id) throws Exception {
		User user = userOperation.acceptFriendRequest(id);
		UserResource resource = userResourceAssembler.toResource(user);
		return new ResponseEntity<UserResource>(resource, HttpStatus.OK);
	}

	@RequestMapping(value = "modify", method = RequestMethod.PUT)
	ResponseEntity<UserResource> modifyUser(@RequestBody UserArg arg) throws Exception {
		User user = userOperation.modify(arg);
		UserResource resource = userResourceAssembler.toResource(user);
		return new ResponseEntity<UserResource>(resource, HttpStatus.OK);

	}
}
