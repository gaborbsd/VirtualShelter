package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Advertisement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdvertisementFacadeInMemoryImpl implements AdvertisementFacade {

	private List<Advertisement> advertisements = new ArrayList<Advertisement>();

	@Override
	public Advertisement findAdvertisementById(long addressId) {
		for(Advertisement addr : advertisements)
			if(addr.getId() == addressId){
				return addr;
			}
		return null;
	}

	@Override
	public List<Advertisement> findAll() {
		return Collections.unmodifiableList(advertisements);
	}

	@Override
	public void create(Advertisement address) {
		advertisements.add(address);
	}

	@Override
	public void edit(Advertisement address) {
		advertisements.set(advertisements.indexOf(address), address);
	}

	@Override
	public void deleteAdvertisementById(long addressId) {
		Advertisement deleteAdvertisement = findAdvertisementById(addressId);

		if(deleteAdvertisement != null)
			advertisements.remove(deleteAdvertisement);
	}

}
