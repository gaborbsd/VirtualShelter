package hu.bme.aut.vshelter.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * Process the authentication requests,
 * returns a fully authenticated object.
 * Registered in application-config.xml
 * 
 * @author Kiss László
 *
 */
@Component
public class VirtualShelterAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
		return new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
