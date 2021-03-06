package hu.bme.aut.vshelter.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "institution", catalog = "vshelter")
public class Institution extends Advertiser {

    @Column(name = "taxNumber", unique = false, nullable = true)
    private String taxNumber;

    @Column(name = "bankAccount", unique = false, nullable = true)
    @Pattern(regexp = "([A-Z]{2}\\d{2} )?(\\d{4} ){5}\\d{4}",
            message = "The correct format: [LLdd ]dddd dddd dddd dddd dddd dddd,"
                    + " where d is a digit L is an uppercase letter ([] means optional)")
    private String bankAccount;

    @Column(name = "payPal", unique = false, nullable = true)
    @Pattern(regexp = "(\\d{4} ){5}\\d{4}",
            message = "The correct format: dddd dddd dddd dddd dddd dddd, where d is a digit")
    private String payPal;

    @ManyToOne
    private User owner;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> institutionAdministrators;

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
        this.payPal = payPal;
    }

    public List<User> getInstitutionAdministrators() {
        return institutionAdministrators;
    }

    public void setInstitutionAdministrators(List<User> institutionAdministrators) {
        this.institutionAdministrators = institutionAdministrators;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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
        Institution other = (Institution) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
