package hu.bme.aut.vshelter.auth;

import org.springframework.security.core.AuthenticationException;

public class VirtualShelterAuthenticationException extends AuthenticationException {

	public VirtualShelterAuthenticationException(String msg) {
		super(msg);
	}

}
