package hu.bme.aut.vshelter.auth;

import java.security.Principal;

public interface VirtualShelterPrincipal extends Principal {
    String getEmail();

    String getPassword();
}
