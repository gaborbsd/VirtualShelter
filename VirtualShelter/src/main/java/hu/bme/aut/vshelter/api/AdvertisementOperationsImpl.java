package hu.bme.aut.vshelter.api;

import hu.bme.aut.vshelter.dal.AdvertisementFacade;
import hu.bme.aut.vshelter.dal.BreedFacade;
import hu.bme.aut.vshelter.dal.InstitutionFacade;
import hu.bme.aut.vshelter.dal.SpeciesFacade;
import hu.bme.aut.vshelter.dal.UserFacade;
import hu.bme.aut.vshelter.dal.impl.AdvertisementFacadeJPAImpl;
import hu.bme.aut.vshelter.dal.impl.BreedFacadeJPAImpl;
import hu.bme.aut.vshelter.dal.impl.InstitutionFacadeJPAImpl;
import hu.bme.aut.vshelter.dal.impl.SpeciesFacadeJPAImpl;
import hu.bme.aut.vshelter.dal.impl.UserFacadeJPAImpl;
import hu.bme.aut.vshelter.entity.Advertisement;
import hu.bme.aut.vshelter.entity.AdvertisementQueryFilter;
import hu.bme.aut.vshelter.entity.Breed;
import hu.bme.aut.vshelter.entity.Institution;
import hu.bme.aut.vshelter.entity.Picture;
import hu.bme.aut.vshelter.entity.Species;
import hu.bme.aut.vshelter.entity.User;

import java.util.List;

public class AdvertisementOperationsImpl implements IAdvertisementOperations {
	
	private AdvertisementFacade advertisementDAO;
	private UserFacade userDAO;
	private InstitutionFacade institutionDAO;
	private SpeciesFacade speciesDAO;
	private BreedFacade breedDAO;
	
	public AdvertisementOperationsImpl() {
		advertisementDAO = new AdvertisementFacadeJPAImpl();
		userDAO = new UserFacadeJPAImpl();
		institutionDAO = new InstitutionFacadeJPAImpl();
		speciesDAO = new SpeciesFacadeJPAImpl();
		breedDAO = new BreedFacadeJPAImpl();
	}

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
		userDAO.create(user);
	}

	@Override
	public void deleteUser(long userId) throws VirtualShelterException {
		userDAO.deleteUserById(userId);
	}

	@Override
	public void updateUser(User user) throws VirtualShelterException {
		userDAO.edit(user);
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
		institutionDAO.create(institution);

	}

	@Override
	public void deleteInstitution(long institutionId)
			throws VirtualShelterException {
		institutionDAO.deleteInstitutionById(institutionId);

	}

	@Override
	public void updateInstitution(Institution institution)
			throws VirtualShelterException {
		institutionDAO.edit(institution);

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
		advertisementDAO.create(advertisement);

	}

	@Override
	public void deleteAdvertisement(long advertisementId)
			throws VirtualShelterException {
		advertisementDAO.deleteAdvertisementById(advertisementId);
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
		return advertisementDAO.findAll();
	}

	@Override
	public List<Species> listAllSpecies() throws VirtualShelterException {
		return speciesDAO.findAll();
	}

	@Override
	public List<Breed> listAllBreeds() throws VirtualShelterException {
		return breedDAO.findAll();
	}

}
