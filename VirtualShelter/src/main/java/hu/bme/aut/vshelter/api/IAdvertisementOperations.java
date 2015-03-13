package hu.bme.aut.vshelter.api;

import hu.bme.aut.vshelter.entity.*;

import java.util.List;

public interface IAdvertisementOperations {
	
	//Advertiser
	public void advertise(Institution instituion, Advertisement advertisement) throws VirtualShelterException;
	
	public void advertise(Advertisement advertisement) throws VirtualShelterException;

	//User
	public void registerUser(User user) throws VirtualShelterException;
	
	public void deleteUser(long userId) throws VirtualShelterException;
	
	public void updateUser(User user) throws VirtualShelterException;
	
	public void uploadPicture(Picture picture, Advertiser advertiser) throws VirtualShelterException;
	
	public void deletePicture(Picture picture, Advertiser advertiser) throws VirtualShelterException; 
	
	public void createInstitution(Institution institution) throws VirtualShelterException;
	
	//Instituion
	public void registerInstitution(Institution institution) throws VirtualShelterException;
	
	public void deleteInstitution(long institutionId) throws VirtualShelterException;
	
	public void updateInstitution(Institution institution) throws VirtualShelterException;
	
	
	public void changeInstitutionOwner(User user, Institution institution) throws VirtualShelterException;
	
	public void addInstitutionAdministrator(User user, Institution institution) throws VirtualShelterException;
	
	public void deleteInstitutionAdministrator(long userId, long institutionId) throws VirtualShelterException;
	
	//Advertisement
	public void createAdvertisement(Advertisement advertisement) throws VirtualShelterException;
	
	public void deleteAdvertisement(long advertisementId) throws VirtualShelterException;
	
	public List<Advertisement> searchAdvertisements(AdvertisementQueryFilter aqf) throws VirtualShelterException;
	
	public List<Advertisement> listAllAdvertisements() throws VirtualShelterException;
	
	//Species, Breed
	public List<Species> listAllSpecies() throws VirtualShelterException;
	
	public List<Breed> listAllBreeds() throws VirtualShelterException;
}
