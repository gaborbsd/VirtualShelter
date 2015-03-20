package hu.bme.aut.vshelter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "institution", catalog = "vshelter")
public class Institution extends Advertiser {
	
	@Column(name = "taxNumber", unique = false, nullable = true)
	private String taxNumber;
	
	@Column(name = "bankAccount", unique = false, nullable = true)
	private String bankAccount;
	
	@Column(name = "PayPal", unique = false, nullable = true)
	private String PayPal;
	
	@Column(name = "longitude", unique = false, nullable = true)
	private double longitude;
	
	@Column(name = "lattitude", unique = false, nullable = true)
	private double lattitude;

	
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
		return PayPal;
	}
	
	public void setPayPal(String payPal) {
		PayPal = payPal;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public double getLattitude() {
		return lattitude;
	}
	
	public void setLattitude(double lattitude) {
		this.lattitude = lattitude;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
