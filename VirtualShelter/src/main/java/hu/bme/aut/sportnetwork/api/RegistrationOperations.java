package hu.bme.aut.sportnetwork.api;

import hu.bme.aut.sportnetwork.entity.User;
import hu.bme.aut.sportnetwork.rest.resources.UserArg;

public interface RegistrationOperations {

	User registrate(UserArg u);
}
