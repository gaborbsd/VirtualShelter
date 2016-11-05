package hu.bme.aut.sportnetwork.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.bme.aut.sportnetwork.api.IUserOperation;

@RestController
@RequestMapping(value="notification")
public class NotificationController {
	
	@Autowired
	private IUserOperation userOperation;
	
	@RequestMapping(value="friend", method=RequestMethod.POST)
	void createNotification(@RequestBody FriendRequestArg arg) {
		userOperation.sendFriendRequest(arg.getTo(), arg.getMessage());
	}

}
