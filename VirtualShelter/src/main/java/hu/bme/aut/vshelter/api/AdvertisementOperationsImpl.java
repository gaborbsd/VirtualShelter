package hu.bme.aut.vshelter.api;

import hu.bme.aut.vshelter.dal.AdvertisementFacade;
import hu.bme.aut.vshelter.dal.BreedFacade;
import hu.bme.aut.vshelter.dal.InstitutionFacade;
import hu.bme.aut.vshelter.dal.SpeciesFacade;
import hu.bme.aut.vshelter.dal.UserFacade;
import hu.bme.aut.vshelter.entity.Advertisement;
import hu.bme.aut.vshelter.entity.AdvertisementQueryFilter;
import hu.bme.aut.vshelter.entity.Breed;
import hu.bme.aut.vshelter.entity.Institution;
import hu.bme.aut.vshelter.entity.Picture;
import hu.bme.aut.vshelter.entity.Species;
import hu.bme.aut.vshelter.entity.User;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;

public class AdvertisementOperationsImpl implements IAdvertisementOperations {
	
	@Inject
	private AdvertisementFacade addressFacade;
	@Inject
	private UserFacade userFacade;
	@Inject
	private InstitutionFacade institutionFacade;
	@Inject
	private SpeciesFacade speciesFacade;
	@Inject
	private AdvertisementFacade advertismentFacade;
	@Inject
	private BreedFacade breedFacade;
	
	@Override
	public void advertise(long instituionID, long advertisementID)
			throws VirtualShelterException {
		// ID - val johet majd es akkor nem is kell ketto

	}

	@Override
	public void advertise(long advertisementId)
			throws VirtualShelterException {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerUser(User user) throws VirtualShelterException {
		userFacade.create(user);
	}

	@Override
	public void deleteUser(long userId) throws VirtualShelterException {
		userFacade.deleteUserById(userId);
	}

	@Override
	public void updateUser(User user) throws VirtualShelterException {
		userFacade.edit(user);
	}

	@Override
	public void uploadPicture(Picture picture, long advertiserId)
			throws VirtualShelterException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePicture(long pictureId, long advertiserId)
			throws VirtualShelterException {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerInstitution(Institution institution)
			throws VirtualShelterException {
		institutionFacade.create(institution);

	}

	@Override
	public void deleteInstitution(long institutionId)
			throws VirtualShelterException {
		institutionFacade.deleteInstitutionById(institutionId);

	}

	@Override
	public void updateInstitution(Institution institution)
			throws VirtualShelterException {
		institutionFacade.edit(institution);

	}

	@Override
	public void changeInstitutionOwner(long userId, long institutionId)
			throws VirtualShelterException {
		// TODO Auto-generated method stub

	}

	@Override
	public void addInstitutionAdministrator(long userId, long institutionId)
			throws VirtualShelterException {
		

	}

	@Override
	public void deleteInstitutionAdministrator(long userId, long institutionId)
			throws VirtualShelterException {
		

	}

	@Override
	public void createAdvertisement(Advertisement advertisement)
			throws VirtualShelterException {
		advertismentFacade.create(advertisement);

	}

	@Override
	public void deleteAdvertisement(long advertisementId)
			throws VirtualShelterException {
		advertismentFacade.deleteAdvertisementById(advertisementId);
	}

	@Override
	public List<Advertisement> searchAdvertisements(AdvertisementQueryFilter aqf)
			throws VirtualShelterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Advertisement> listAllAdvertisements()
			throws VirtualShelterException {
		return advertismentFacade.findAll();
	}

	@Override
	public List<Species> listAllSpecies() throws VirtualShelterException {
		return speciesFacade.findAll();
	}

	@Override
	public List<Breed> listAllBreeds() throws VirtualShelterException {
		return breedFacade.findAll();
	}

}
