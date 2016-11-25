package hu.bme.aut.sportnetwork.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hu.bme.aut.sportnetwork.api.NotificationOperations;
import hu.bme.aut.sportnetwork.api.SportEventOperations;
import hu.bme.aut.sportnetwork.api.UserOperations;
import hu.bme.aut.sportnetwork.entity.Notification;
import hu.bme.aut.sportnetwork.entity.User;
import hu.bme.aut.sportnetwork.rest.resources.FriendRequestArg;
import hu.bme.aut.sportnetwork.rest.resources.NotificationResource;
import hu.bme.aut.sportnetwork.rest.resources.NotificationResourceAssembler;
import hu.bme.aut.sportnetwork.rest.resources.RateMembersRet;
import hu.bme.aut.sportnetwork.rest.resources.SportEventShortResource;
import hu.bme.aut.sportnetwork.rest.resources.RateUsersArg;

@RestController
@RequestMapping(value="notification")
public class NotificationController {
	
	@Autowired
	private UserOperations userOperation;
	
	@Autowired
	private SportEventOperations eventOperation;
	
	@Autowired
	private NotificationOperations notificationOperation;

	@Autowired
	private NotificationResourceAssembler notificationResourceAssembler;

	@RequestMapping(value = "get", method = RequestMethod.GET)
	ResponseEntity<List<NotificationResource>> getAll() {
		List<Notification> notifications = notificationOperation.getNotifications();
		List<NotificationResource> resourceList = notificationResourceAssembler.toResources(notifications);
		return new ResponseEntity<List<NotificationResource>>(resourceList, HttpStatus.OK);
	}

	@RequestMapping(value="friend", method=RequestMethod.POST)
	ResponseEntity<String> sendFriendRequest(@RequestBody FriendRequestArg arg) {
		userOperation.sendFriendRequest(arg.getTo());
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value="friendAccept/{id}", method=RequestMethod.PUT)
	ResponseEntity<String> acceptFriendRequest(@PathVariable Long id) throws Exception {
		userOperation.acceptFriendRequest(id);
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value="eventAccept/{id}", method=RequestMethod.PUT)
	ResponseEntity<String> acceptEventRequest(@PathVariable Long id) throws Exception {
		eventOperation.acceptEventRequest(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "decline/{id}", method = RequestMethod.PUT)
	ResponseEntity<List<NotificationResource>> declineRequest(@PathVariable Long id) throws Exception {
		List<Notification> notifications = notificationOperation.declineRequest(id);
		List<NotificationResource> resourceList = notificationResourceAssembler.toResources(notifications);
		return new ResponseEntity<List<NotificationResource>>(resourceList, HttpStatus.OK);
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	ResponseEntity<String> deleteNotification(@PathVariable Long id) throws Exception {
		notificationOperation.deleteNotification(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "rate/{id}", method = RequestMethod.GET)
	@ResponseBody
	RateMembersRet getMembersToRate(@PathVariable Long id) {
		List<User> users = eventOperation.getMembersOfSportEvent(id);
		RateMembersRet ret = new RateMembersRet(users);
		return ret;
	}

	@RequestMapping(value = "rate", method = RequestMethod.POST)
	ResponseEntity<String> rateToUsers(@RequestBody RateUsersArg arg) throws Exception {
		eventOperation.rateUsers(arg);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
