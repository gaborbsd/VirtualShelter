package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.entity.Advertisement;
import hu.bme.aut.vshelter.entity.Advertiser;
import hu.bme.aut.vshelter.entity.Animal;

import java.util.List;

public interface AdvertisementRepositoryCustom extends AbstractRepositoryCustom<Advertisement> {
	
	public List<Advertisement> listAdvertisementsFromAdvertiser(long advertiserId) throws VirtualShelterException;
	
	public Advertiser getAdvertiserOfAnimal(long animalId) throws VirtualShelterException;
	
	public List<Animal> listAnimalsAdvertisedByAdvertiser(long advertiserId) throws VirtualShelterException;

}
