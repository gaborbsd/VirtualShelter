package hu.bme.aut.vshelter.auth;

import java.io.Serializable;

public class VirtualShelterUserPrincipal implements Serializable, VirtualShelterPrincipal {
    private static final long serialVersionUID = -7036029646338633656L;

    private String email;

    private String password;

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
