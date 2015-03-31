package hu.bme.aut.vshelter.rest.resources;

import java.util.Calendar;
import java.util.List;

import hu.bme.aut.vshelter.entity.Address;
import hu.bme.aut.vshelter.entity.AdoptionType;
import hu.bme.aut.vshelter.entity.Breed;
import hu.bme.aut.vshelter.entity.Disease;
import hu.bme.aut.vshelter.entity.Handicap;
import hu.bme.aut.vshelter.entity.Sex;
import hu.bme.aut.vshelter.entity.VaccinationStatus;

import org.springframework.hateoas.ResourceSupport;

public class AnimalResource extends ResourceSupport {
	private String name;
	private AdoptionType adoptionType;
	private Breed breed;
	private int height;
	private int weight;
	private String age;
	private Sex sex;
	private boolean spayed;
	private VaccinationStatus vaccinationStatus;
	private String description;
	private List<Disease> knownDiseases;
	private List<Handicap> knownHandicaps;
	private Address address;
	
	public AnimalResource() {
		super();
	}

	public AnimalResource(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AdoptionType getAdoptionType() {
		return adoptionType;
	}

	public void setAdoptionType(AdoptionType adoptionType) {
		this.adoptionType = adoptionType;
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
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
}
