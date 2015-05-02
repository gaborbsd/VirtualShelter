package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Advertisement;
import hu.bme.aut.vshelter.entity.Advertiser;
import hu.bme.aut.vshelter.entity.Animal;

import java.util.List;

public interface AdvertisementFacade {
	
	Advertisement findAdvertisementById(long advertisementId);
	
	List<Advertisement> findAll();
	
	void create(Advertisement advertisement);
	
	void edit(Advertisement advertisement);
	
	void deleteAdvertisementById(long advertisementId);
	
	public List<Advertisement> listAdvertisementsFromAdvertiser(long advertiserId);
	
	public Advertiser getAdvertiserOfAnimal(long animalId);
	
	public List<Animal> listAnimalsAdvertisedByAdvertiser(long advertiserId);

}
