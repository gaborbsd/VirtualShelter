package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.dal.AdvertisementFacade;
import hu.bme.aut.vshelter.entity.Advertisement;
import hu.bme.aut.vshelter.entity.Advertiser;
import hu.bme.aut.vshelter.entity.Animal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class AdvertisementFacadeInMemoryImpl implements AdvertisementFacade {

	private List<Advertisement> advertisements = new ArrayList<Advertisement>();

	@Override
	public Advertisement findById(long advertisementId) {
		try {
			return advertisements.stream()
					.filter(a -> a.getId() == advertisementId).findFirst()
					.get();
		} catch (NoSuchElementException e) {
			return null;
		}

	}

	@Override
	public List<Advertisement> findAll() throws VirtualShelterException {
		return Collections.unmodifiableList(advertisements);
	}

	@Override
	public void create(Advertisement advertisement) throws VirtualShelterException {
		advertisements.add(advertisement);
	}

	@Override
	public void edit(Advertisement advertisement) throws VirtualShelterException {
		advertisements
				.set(advertisements.indexOf(advertisement), advertisement);
	}

	@Override
	public void deleteById(long advertisementId) throws VirtualShelterException {
		Advertisement deleteAdvertisement = findById(advertisementId);

		if (deleteAdvertisement != null)
			advertisements.remove(deleteAdvertisement);
	}

	@Override
	public List<Advertisement> listAdvertisementsFromAdvertiser (
			long advertiserId) throws VirtualShelterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Advertiser getAdvertiserOfAnimal(long animalId) throws VirtualShelterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Animal> listAnimalsAdvertisedByAdvertiser(long advertiserId)  throws VirtualShelterException{
		// TODO Auto-generated method stub
		return null;
	}

}
