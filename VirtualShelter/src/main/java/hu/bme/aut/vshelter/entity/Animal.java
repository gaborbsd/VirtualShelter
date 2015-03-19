package hu.bme.aut.vshelter.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Animal {
	@Id
	@GeneratedValue
	private int id;

	private String name;
	
	//private AdoptionType adoptionTpye;
	
	//private Breed breed;
	
	private int height;
	
	private int weight;
	
	//private Date age;
	
	//private Sex sex;
	
	private boolean spayed;
	
	//private VaccinationStatus vaccinationStatus;
	
	private String description;
	
	//private List<String> knownDiseases;
	
	//private List<String> knownHandicaps;
	
	//private Address address;
	
	//private List<Picture> picture;
	
	//private DeliveryType deliveryType;
	
	private String otherCosts;
	
	//private Advertisement advertisement;
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	/*
	public AdoptionType getAdoptionTpye() {
		return adoptionTpye;
	}
	
	public void setAdoptionTpye(AdoptionType adoptionTpye) {
		this.adoptionTpye = adoptionTpye;
	}
	
	public Breed getBreed() {
		return breed;
	}
	
	public void setBreed(Breed breed) {
		this.breed = breed;
	}
	*/
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	/*
	public Date getAge() {
		return age;
	}
	
	public void setAge(Date age) {
		this.age = age;
	}
	
	public Sex getSex() {
		return sex;
	}
	
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	*/
	public boolean isSpayed() {
		return spayed;
	}
	
	public void setSpayed(boolean spayed) {
		this.spayed = spayed;
	}
	/*
	public VaccinationStatus getVaccinationStatus() {
		return vaccinationStatus;
	}
	
	public void setVaccinationStatus(VaccinationStatus vaccinationStatus) {
		this.vaccinationStatus = vaccinationStatus;
	}
	*/
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	/*
	public List<String> getKnownDiseases() {
		return knownDiseases;
	}
	
	public void setKnownDiseases(List<String> knownDiseases) {
		this.knownDiseases = knownDiseases;
	}
	
	public List<String> getKnownHandicaps() {
		return knownHandicaps;
	}
	
	public void setKnownHandicaps(List<String> knownHandicaps) {
		this.knownHandicaps = knownHandicaps;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public List<Picture> getPicture() {
		return picture;
	}
	
	public void setPicture(List<Picture> picture) {
		this.picture = picture;
	}
	
	public DeliveryType getDeliveryType() {
		return deliveryType;
	}
	
	public void setDeliveryType(DeliveryType deliveryType) {
		this.deliveryType = deliveryType;
	}
	*/
	public String getOtherCosts() {
		return otherCosts;
	}
	
	public void setOtherCosts(String otherCosts) {
		this.otherCosts = otherCosts;
	}
	/*
	public Advertisement getAdvertisement() {
		return advertisement;
	}
	
	public void setAdvertisement(Advertisement advertisement) {
		this.advertisement = advertisement;
	}
*/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		Animal other = (Animal) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
