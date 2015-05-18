package hu.bme.aut.vshelter.auth;

import hu.bme.aut.vshelter.api.IAuthenticationAndPermissionOperations;
import hu.bme.aut.vshelter.api.VirtualShelterException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

	@Autowired
	private IAuthenticationAndPermissionOperations operations;
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		try{
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		if (operations.authenticate(name, password)) {
			List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
			grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
			return new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
		} else {
			throw new VirtualShelterAuthenticationException("Authentication unsuccessful");
		}
		}catch(VirtualShelterException e){
			throw new VirtualShelterAuthenticationException("Authentication unsuccessful");
		}
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
