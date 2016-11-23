package hu.bme.aut.sportnetwork.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

@Component
public class SportnetworkSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	private static final String USER_HOME = "/index.html";
	
	private static final String ADMIN_HOME = "/html/admin/admin.html";
	
	private static final String ERROR_HOME = "/error.html";
	
	@Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        String targetUrl = determineTargetUrl(authentication);
 
        if (response.isCommitted()) {
            throw new RuntimeException("FUCK YOU");
        }
 
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
	
	 protected String determineTargetUrl(Authentication authentication) {
		 Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		 
		 List<String> roles = new ArrayList<>();
		 
		 authorities.forEach(a -> roles.add(a.getAuthority()));
		 
		 String url = null;
		 
		 if (roles.contains(Roles.USER)) {
			 url = USER_HOME;
		 } else if (roles.contains(Roles.ADMIN)) {
			url = ADMIN_HOME;
		 } else {
			url = ERROR_HOME;
		 }
		  
		 return url;
	 }
}
