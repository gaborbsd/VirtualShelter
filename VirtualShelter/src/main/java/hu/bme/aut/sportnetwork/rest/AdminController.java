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
import hu.bme.aut.sportnetwork.rest.resources.RequestArg;

@RestController
@RequestMapping(value = "admin")
public class AdminController {

	@Autowired
	UserOperations userOperation;

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
}
