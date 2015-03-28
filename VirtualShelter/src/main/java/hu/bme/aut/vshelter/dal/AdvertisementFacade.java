package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Advertisement;

import java.util.List;

public interface AdvertisementFacade {
	
	Advertisement findAdvertisementById(int advertisementId);
	
	List<Advertisement> findAll();
	
	void create(Advertisement advertisement);
	
	void edit(Advertisement advertisement);
	
	void deleteAdvertisementById(int advertisementId);

}