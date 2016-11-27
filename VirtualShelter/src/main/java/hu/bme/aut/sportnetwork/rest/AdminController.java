package hu.bme.aut.sportnetwork.rest;

import java.util.ArrayList;
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

import hu.bme.aut.sportnetwork.api.SportEventOperations;
import hu.bme.aut.sportnetwork.api.UserOperations;
import hu.bme.aut.sportnetwork.entity.Comment;
import hu.bme.aut.sportnetwork.entity.SportEvent;
import hu.bme.aut.sportnetwork.rest.resources.CommentWrapper;
import hu.bme.aut.sportnetwork.rest.resources.RequestArg;
import hu.bme.aut.sportnetwork.rest.resources.SportEventShortResource;
import hu.bme.aut.sportnetwork.rest.resources.SportEventShortResourceAssembler;
import hu.bme.aut.sportnetwork.rest.resources.StringArg;
import hu.bme.aut.sportnetwork.rest.resources.UserLinkWrapper;

@RestController
@RequestMapping(value = "admin")
public class AdminController {

	@Autowired
	UserOperations userOperation;

	@Autowired
	private SportEventOperations sporteventOperation;

	@Autowired
	private SportEventShortResourceAssembler sportEventShortResourceAssembler;

	@RequestMapping(value = "warn", method = RequestMethod.PUT)
	ResponseEntity<String> warnUser(@RequestBody RequestArg arg) {
		userOperation.warnUser(arg.getTo(), arg.getMessage());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "delete/user/{id}", method = RequestMethod.DELETE)
	ResponseEntity<String> warnUser(@PathVariable Long id) {
		userOperation.deleteUser(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "events", method = RequestMethod.POST)
	ResponseEntity<List<SportEventShortResource>> getEvents(@RequestBody StringArg arg) {
		List<SportEvent> events = sporteventOperation.findByTitle(arg.getValue());
		List<SportEventShortResource> resourceList = sportEventShortResourceAssembler.toResources(events);
		return new ResponseEntity<List<SportEventShortResource>>(resourceList, HttpStatus.OK);
	}

	@RequestMapping(value = "comments/{id}", method = RequestMethod.GET)
	@ResponseBody
	List<CommentWrapper> getComments(@PathVariable Long id) {
		List<Comment> comments = sporteventOperation.getCommentsOfEvent(id);
		List<CommentWrapper> ret = new ArrayList<>();

		comments.forEach(c -> ret.add(toWrapper(c)));

		return ret;
	}

	private CommentWrapper toWrapper(Comment c) {
		CommentWrapper w = new CommentWrapper();
		w.setId(c.getId());
		w.setMessage(c.getMessage());
		UserLinkWrapper uw = new UserLinkWrapper();
		uw.setName(c.getOwner().getName());
		w.setWriter(uw);
		return w;
	}
}
