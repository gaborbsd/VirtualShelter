package hu.bme.aut.vshelter.rest.resources;

import hu.bme.aut.vshelter.entity.Advertiser;
import hu.bme.aut.vshelter.entity.Animal;
import org.springframework.hateoas.ResourceSupport;

import java.util.Calendar;

public class AdvertisementResource extends ResourceSupport {
    private long advertisementId;
    private String title;
    private String description;
    private Advertiser advertiser;
    private Animal animal;
    private Calendar dateOfAdvertisement;


    public long getAdvertisementId() {
        return advertisementId;
    }


    public void setAdvertisementId(long advertisementId) {
        this.advertisementId = advertisementId;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

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
