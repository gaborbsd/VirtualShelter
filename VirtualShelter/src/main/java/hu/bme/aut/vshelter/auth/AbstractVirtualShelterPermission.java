package hu.bme.aut.vshelter.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;

public abstract class AbstractVirtualShelterPermission implements Permission {
	
	@Autowired
	protected ApplicationContext context;
	
	protected boolean isAuthenticated(Authentication authentication) {
		return authentication!=null && authentication.getPrincipal() instanceof VirtualShelterPrincipal;
	}
}
