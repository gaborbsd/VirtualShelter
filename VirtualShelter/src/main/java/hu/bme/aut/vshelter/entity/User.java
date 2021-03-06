package hu.bme.aut.vshelter.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "user", catalog = "vshelter")
public class User extends Advertiser {

    @Column(name = "password", unique = false, nullable = false)
    @NotNull
    private String password;

    @Column(name = "introductionText", unique = false, nullable = true)
    private String introductionText;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIntroductionText() {
        return introductionText;
    }

    public void setIntroductionText(String introductionText) {
        this.introductionText = introductionText;
    }

    public Set<String> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
