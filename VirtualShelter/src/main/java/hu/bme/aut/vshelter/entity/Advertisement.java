package hu.bme.aut.vshelter.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Advertisement {
	@Id
	@GeneratedValue
	private int id;
	
	private Advertiser advertiser;
	
	private Animal animal;
	
	private Calendar dateOfAdvertisement;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		Advertisement other = (Advertisement) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
