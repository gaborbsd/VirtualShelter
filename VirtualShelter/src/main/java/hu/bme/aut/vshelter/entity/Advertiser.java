package hu.bme.aut.vshelter.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "advertiser", catalog = "vshelter", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "name")})
public abstract class Advertiser {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    protected long id;

    @Column(name = "name", unique = true, nullable = false)
    @NotNull
    @Size(min = 1, message = "may not be empty")
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
            message = "Invalid email format")
    private String email;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Address address;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private Picture profilePicture;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Picture> picturesList;

    @Column(name = "phoneNumber", unique = false, nullable = true)
    @Pattern(regexp = "([+]|[0][0])[0-9]{8,11}",
            message = "Invalid phoneNumber format, valid: + or 00 then 8-11 numbers")
    private String phoneNumber;

    @Column(name = "mobilePhoneNumber", unique = false, nullable = true)
    @Pattern(regexp = "([+]|[0][0])[0-9]{8,11}",
            message = "Invalid mobilePhoneNumber format, valid: + or 00 then 8-11 numbers")
    private String mobilePhoneNumber;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Picture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Picture profilePicture) {
        this.profilePicture = profilePicture;
    }

    public List<Picture> getPicturesList() {
        return picturesList;
    }

    public void setPicturesList(List<Picture> picturesList) {
        this.picturesList = picturesList;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
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
        Advertiser other = (Advertiser) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
