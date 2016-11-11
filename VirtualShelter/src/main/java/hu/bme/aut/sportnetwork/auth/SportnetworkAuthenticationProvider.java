package hu.bme.aut.sportnetwork.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import hu.bme.aut.sportnetwork.dal.UserDAO;
import hu.bme.aut.sportnetwork.entity.User;

public class SportnetworkAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	UserDAO userRepository;

	@Override
	public Authentication authenticate(Authentication authentication){
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		User u = userRepository.findByName(name);
		if (u != null && password.equals(u.getPassword())) {
			List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
			grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
			return new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
		} 
		throw new RuntimeException("UNSUCCESFULL AUT");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
