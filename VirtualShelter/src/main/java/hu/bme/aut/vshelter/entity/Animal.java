package hu.bme.aut.vshelter.entity;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "animal", catalog = "vshelter")
public class Animal {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "adoptionType", unique = false, nullable = true)
    private AdoptionType adoptionType;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private Breed breed;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private Species species;

    @Column(name = "height", unique = false, nullable = true)
    private int height;

    @Column(name = "weight", unique = false, nullable = true)
    private int weight;

    @Column(name = "age", unique = false, nullable = true)
    @Past
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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Disease> knownDiseases;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Handicap> knownHandicaps;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private Address address;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Picture profilePicture;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Picture> picturesList;

    @Enumerated(EnumType.STRING)
    @Column(name = "deliveryType", unique = false, nullable = true)
    private DeliveryType deliveryType;

    @Column(name = "otherCosts", unique = false, nullable = true)
    private String otherCosts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
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

    public Picture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Picture profilePicture) {
        this.profilePicture = profilePicture;
    }

    public List<Picture> getPicturesList() {
        return picturesList;
    }

    public void setPictureList(List<Picture> picturesList) {
        this.picturesList = picturesList;
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
        Animal other = (Animal) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
