package hu.bme.aut.sportnetwork.auth;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import hu.bme.aut.sportnetwork.dal.UserRepository;
import hu.bme.aut.sportnetwork.entity.User;

public class SportnetworkAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	UserRepository userRepository;

	static Logger LOGGER = LoggerFactory.getLogger(SportnetworkAuthenticationProvider.class);

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		User u = getUser(name);

		byte[] salt = AuthOperations.toByteArray(u.getSalt());

		password = AuthOperations.get_SHA_1_SecurePassword(password, salt);

		LOGGER.info(password);

		if (u != null && password.equals(u.getPassword()) && !u.getDeleted()) {
			List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
			u.getRoles().forEach(r -> grantedAuths.add(new SimpleGrantedAuthority(r)));
			return new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
		}
		return null;
	}

	private User getUser(String name) throws BadCredentialsException {
		User u = userRepository.findByName(name);

		if (u == null) {
			u = userRepository.findByEmail(name);
		}

		if (u == null) {
			throw new BadCredentialsException("UserName not found");
		}

		return u;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
