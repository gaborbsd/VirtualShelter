package hu.bme.aut.vshelter.auth;

import org.springframework.security.core.Authentication;

public class VirtualShelterUserAccessToAnimal extends AbstractVirtualShelterPermission {

    @Override
    public boolean isAllowed(Authentication authentication, Object object) {
        //TODO
        return true;
    }

}
