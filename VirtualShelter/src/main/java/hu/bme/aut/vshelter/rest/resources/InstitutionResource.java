package hu.bme.aut.vshelter.rest.resources;

import hu.bme.aut.vshelter.entity.Address;
import hu.bme.aut.vshelter.entity.User;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class InstitutionResource extends ResourceSupport {

    private long institutionId;
    private String email;
    private String name;
    private Address address;
    private String taxNumber;
    private String bankAccount;
    private String payPal;
    private String phoneNumber;
    private String mobilePhoneNumber;
    private List<User> institutionAdministrators;

    public long getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(long institutionId) {
        this.institutionId = institutionId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getPayPal() {
        return payPal;
    }

    public void setPayPal(String payPal) {
        payPal = payPal;
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

    public List<User> getInstitutionAdministrators() {
        return institutionAdministrators;
    }

    public void setInstitutionAdministrators(List<User> institutionAdministrators) {
        this.institutionAdministrators = institutionAdministrators;
    }


}
