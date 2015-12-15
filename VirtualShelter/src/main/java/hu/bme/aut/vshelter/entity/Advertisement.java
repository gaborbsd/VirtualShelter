package hu.bme.aut.vshelter.entity;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "advertisement", catalog = "vshelter")
public class Advertisement {
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	
	@Column(name = "title", unique = true, nullable = false)
	private String title;

	@Column(name = "description", unique = false, nullable = true)
	private String description;

	@ManyToOne(cascade = CascadeType.MERGE)
	private Advertiser advertiser;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Animal animal;
	
	@Column(name = "dateOfAdvertisement", unique = true, nullable = true)
	private Calendar dateOfAdvertisement;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
		
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		result = prime * result + (int)id;
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
