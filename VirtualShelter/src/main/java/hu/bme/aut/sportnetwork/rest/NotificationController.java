package hu.bme.aut.sportnetwork.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.bme.aut.sportnetwork.api.UserOperations;

@RestController
@RequestMapping(value="notification")
public class NotificationController {
	
	@Autowired
	private UserOperations userOperation;
	
	@RequestMapping(value="friend", method=RequestMethod.POST)
	void sendFriendRequest(@RequestBody FriendRequestArg arg) {
		userOperation.sendFriendRequest(arg.getTo(), arg.getMessage());
	}
	
	@RequestMapping(value="friendAccept/{id}", method=RequestMethod.POST)
	void acceptFriendRequest(@PathVariable Long id) throws Exception {
		userOperation.acceptFriendRequest(id);
	}

}
