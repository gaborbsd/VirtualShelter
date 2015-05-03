package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Advertisement;
import hu.bme.aut.vshelter.entity.Advertiser;
import hu.bme.aut.vshelter.entity.Animal;

import java.util.List;

public interface AdvertisementFacade extends AbstractFacade<Advertisement> {
	
	public List<Advertisement> listAdvertisementsFromAdvertiser(long advertiserId);
	
	public Advertiser getAdvertiserOfAnimal(long animalId);
	
	public List<Animal> listAnimalsAdvertisedByAdvertiser(long advertiserId);

}
