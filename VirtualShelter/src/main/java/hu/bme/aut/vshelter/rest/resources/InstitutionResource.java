package hu.bme.aut.vshelter.rest.resources;

import hu.bme.aut.vshelter.entity.Address;

import org.springframework.hateoas.ResourceSupport;

public class InstitutionResource extends ResourceSupport {
	private String name;
	private Address address;
	private String taxNumber;
	private String bankAccount;
	private String PayPal;
	
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
		return PayPal;
	}
	public void setPayPal(String payPal) {
		PayPal = payPal;
	}
}
