package hu.bme.aut.sportnetwork.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.bme.aut.sportnetwork.api.UserOperations;
import hu.bme.aut.sportnetwork.rest.resources.FriendRequestArg;

@RestController
@RequestMapping(value="notification")
public class NotificationController {
	
	@Autowired
	private UserOperations userOperation;
	
	@RequestMapping(value="friend", method=RequestMethod.POST)
	ResponseEntity<String> sendFriendRequest(@RequestBody FriendRequestArg arg) {
		userOperation.sendFriendRequest(arg.getTo(), arg.getMessage());
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value="friendAccept/{id}", method=RequestMethod.POST)
	ResponseEntity<String> acceptFriendRequest(@PathVariable Long id) throws Exception {
		userOperation.acceptFriendRequest(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
