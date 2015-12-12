package hu.bme.aut.vshelter.rest.resources;

import hu.bme.aut.vshelter.entity.Advertiser;
import hu.bme.aut.vshelter.entity.Animal;

import java.util.Calendar;


import org.springframework.hateoas.ResourceSupport;

public class AdvertisementResource extends ResourceSupport {
	
	private String description;


	private Advertiser advertiser;
	

	private Animal animal;
	

	private Calendar dateOfAdvertisement;
	

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Advertiser getAdvertiser() {
		return advertiser;
	}


	public void setAdvertiser(Advertiser advertiser) {
		this.advertiser = advertiser;
	}


	public Animal getAnimal() {
		return animal;
	}


	public void setAnimal(Animal animal) {
		this.animal = animal;
	}


	public Calendar getDateOfAdvertisement() {
		return dateOfAdvertisement;
	}


	public void setDateOfAdvertisement(Calendar dateOfAdvertisement) {
		this.dateOfAdvertisement = dateOfAdvertisement;
	}


	

}
