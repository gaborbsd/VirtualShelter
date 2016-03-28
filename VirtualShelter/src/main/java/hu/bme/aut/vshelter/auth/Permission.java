package hu.bme.aut.vshelter.auth;

import org.springframework.security.core.Authentication;

public interface Permission {

    boolean isAllowed(Authentication authentication, Object object);

}
