package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.dal.AdvertisementFacade;
import hu.bme.aut.vshelter.entity.Advertisement;
import hu.bme.aut.vshelter.entity.Advertiser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class AdvertisementFacadeInMemoryImpl implements AdvertisementFacade {

	private List<Advertisement> advertisements = new ArrayList<Advertisement>();

	@Override
	public Advertisement findAdvertisementById(long advertisementId) {
		try {
			return advertisements.stream()
					.filter(a -> a.getId() == advertisementId).findFirst()
					.get();
		} catch (NoSuchElementException e) {
			return null;
		}

	}

	@Override
	public List<Advertisement> findAll() {
		return Collections.unmodifiableList(advertisements);
	}

	@Override
	public void create(Advertisement advertisement) {
		advertisements.add(advertisement);
	}

	@Override
	public void edit(Advertisement advertisement) {
		advertisements
				.set(advertisements.indexOf(advertisement), advertisement);
	}

	@Override
	public void deleteAdvertisementById(long advertisementId) {
		Advertisement deleteAdvertisement = findAdvertisementById(advertisementId);

		if (deleteAdvertisement != null)
			advertisements.remove(deleteAdvertisement);
	}

	@Override
	public List<Advertisement> listAdvertisementsFromAdvertiser(
			long advertiserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Advertiser getAdvertiserOfAnimal(long animalId) {
		// TODO Auto-generated method stub
		return null;
	}

}
