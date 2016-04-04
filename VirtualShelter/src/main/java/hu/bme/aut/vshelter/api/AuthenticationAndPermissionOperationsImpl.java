package hu.bme.aut.vshelter.api;

import hu.bme.aut.vshelter.dal.UserRepository;
import hu.bme.aut.vshelter.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthenticationAndPermissionOperationsImpl implements
        IAuthenticationAndPermissionOperations {

    // Before Spring Data we used these Facades
//	@Inject
//	private UserFacade userFacade;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean authenticate(String email, String password) throws VirtualShelterException {
        User u = userRepository.getUserByEmail(email);
        return passwordEncoder.matches(password, u.getPassword());
    }

}
