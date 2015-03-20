package hu.bme.aut.vshelter.entity;

import java.util.Date;
import java.util.List;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "animal", catalog = "vshelter")
public class Animal {
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "name", unique = false, nullable = false)
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "adoptionTpye", unique = false, nullable = true)
	AdoptionType adoptionTpye;
	
	@ManyToOne
	private Breed breed;
	
	@Column(name = "height", unique = false, nullable = true)
	private int height;
	
	@Column(name = "weight", unique = false, nullable = true)
	private int weight;
	
	@Column(name = "age", unique = false, nullable = true)
	private Calendar age;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "sex", unique = false, nullable = true)
	private Sex sex;
	
	@Column(name = "spayed", unique = false, nullable = true)
	private boolean spayed;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "vaccinationStatus", unique = false, nullable = true)
	private VaccinationStatus vaccinationStatus;
	
	@Column(name = "description", unique = false, nullable = true)
	private String description;
	
	@ManyToMany
	private List<Disease> knownDiseases;
	
	@ManyToMany
	private List<Handicap> knownHandicaps;
	
	@ManyToOne
	private Address address;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Picture> picture;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "deliveryType", unique = false, nullable = true)
	private DeliveryType deliveryType;
	
	@Column(name = "otherCosts", unique = false, nullable = true)
	private String otherCosts;
	
	@ManyToOne
	private Advertisement advertisement;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
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
	
	public Calendar getAge() {
		return age;
	}
	
	public void setAge(Calendar age) {
		this.age = age;
	}
	
	public Sex getSex() {
		return sex;
	}
	
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	
	public boolean isSpayed() {
		return spayed;
	}
	
	public void setSpayed(boolean spayed) {
		this.spayed = spayed;
	}
	
	public VaccinationStatus getVaccinationStatus() {
		return vaccinationStatus;
	}
	
	public void setVaccinationStatus(VaccinationStatus vaccinationStatus) {
		this.vaccinationStatus = vaccinationStatus;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Disease> getKnownDiseases() {
		return knownDiseases;
	}
	
	public void setKnownDiseases(List<Disease> knownDiseases) {
		this.knownDiseases = knownDiseases;
	}
	
	public List<Handicap> getKnownHandicaps() {
		return knownHandicaps;
	}
	
	public void setKnownHandicaps(List<Handicap> knownHandicaps) {
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
	
	public String getOtherCosts() {
		return otherCosts;
	}
	
	public void setOtherCosts(String otherCosts) {
		this.otherCosts = otherCosts;
	}
	
	public Advertisement getAdvertisement() {
		return advertisement;
	}
	
	public void setAdvertisement(Advertisement advertisement) {
		this.advertisement = advertisement;
	}

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
