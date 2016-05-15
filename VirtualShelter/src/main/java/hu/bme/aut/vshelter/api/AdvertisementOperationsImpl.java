package hu.bme.aut.vshelter.api;

import hu.bme.aut.vshelter.dal.*;
import hu.bme.aut.vshelter.entity.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.List;

public class AdvertisementOperationsImpl implements IAdvertisementOperations {

    // Before Spring Data we used these Facades
//	@Inject
//	private UserFacade userFacade;
//	@Inject
//	private InstitutionFacade institutionFacade;
//	@Inject
//	private SpeciesFacade speciesFacade;
//	@Inject
//	private AdvertisementFacade advertismentFacade;
//	@Inject
//	private BreedFacade breedFacade;
//	@Inject
//	private AnimalFacade animalFacade;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private SpeciesRepository speciesRepository;
    @Autowired
    private AdvertisementRepository advertisementRepository;
    @Autowired
    private BreedRepository breedRepository;
    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public void advertise(long instituionId, long animalId)
            throws VirtualShelterException {
        Advertisement advertisement = new Advertisement();
        Institution institution = institutionRepository.findOne(Long.valueOf(instituionId));
        advertisement.setAdvertiser(institution);
        Animal animal = animalRepository.findOne(Long.valueOf(animalId));
        advertisement.setAnimal(animal);
        advertisement.setDateOfAdvertisement(Calendar.getInstance());
        advertisementRepository.save(advertisement);

    }

    @Override
    public void advertise(long animalId)
            throws VirtualShelterException {
        Advertisement advertisement = new Advertisement();
        User user = userRepository.findOne(Long.valueOf(1));
        advertisement.setAdvertiser(user);
        Animal animal = animalRepository.findOne(Long.valueOf(animalId));
        advertisement.setAnimal(animal);
        advertisement.setDateOfAdvertisement(Calendar.getInstance());
        advertisementRepository.save(advertisement);
    }

    @Override
    public void registerUser(User user) throws VirtualShelterException {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(long userId) throws VirtualShelterException {
        userRepository.delete(Long.valueOf(userId));
    }

    @Override
    public void updateUser(User user) throws VirtualShelterException {
        userRepository.edit(user);
    }

    @Override
    public void uploadPicture(Picture picture, long advertiserId)
            throws VirtualShelterException {
        // user vagy institution ??

    }

    @Override
    public void deletePicture(long pictureId, long advertiserId)
            throws VirtualShelterException {
        // TODO Auto-generated method stub

    }

    @Override
    public void registerInstitution(Institution institution)
            throws VirtualShelterException {
        institutionRepository.save(institution);

    }

    @Override
    public void deleteInstitution(long institutionId)
            throws VirtualShelterException {
        institutionRepository.delete(Long.valueOf(institutionId));

    }

    @Override
    public void updateInstitution(Institution institution)
            throws VirtualShelterException {
        institutionRepository.edit(institution);

    }

    @Override
    public void changeInstitutionOwner(long userId, long institutionId)
            throws VirtualShelterException {
        User owner = userRepository.findOne(Long.valueOf(userId));
        Institution institution = institutionRepository.findOne(Long.valueOf(institutionId));
        institution.setOwner(owner);
        institutionRepository.edit(institution);

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
    public List<User> listInstitutionAdministrators(long institutionId) throws VirtualShelterException {
        Institution institution = institutionRepository.findOne(Long.valueOf(institutionId));
        return institution.getInstitutionAdministrators();
    }

    @Override
    public void createAdvertisement(Advertisement advertisement)
            throws VirtualShelterException {
        advertisement.setDateOfAdvertisement(Calendar.getInstance());
        advertisementRepository.save(advertisement);

    }

    @Override
    public void deleteAdvertisement(long advertisementId)
            throws VirtualShelterException {
        advertisementRepository.delete(Long.valueOf(advertisementId));
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
        return advertisementRepository.findAll();
    }

    @Override
    public List<Species> listAllSpecies() throws VirtualShelterException {
        return speciesRepository.findAll();
    }

    @Override
    public List<Breed> listAllBreeds() throws VirtualShelterException {
        return breedRepository.findAll();
    }

    @Override
    public void addAnimal(Animal animal) throws VirtualShelterException {
        animalRepository.save(animal);
    }

    @Override
    public void deleteAnimal(long animalId) throws VirtualShelterException {
        animalRepository.delete(Long.valueOf(animalId));
    }

    @Override
    public List<Advertisement> listAdvertisementsFromAdvertiser(
            long advertiserId) throws VirtualShelterException {
        return advertisementRepository.listAdvertisementsFromAdvertiser(advertiserId);
    }

    @Override
    public Species findSpeciesById(long speciesId) throws VirtualShelterException {
        return speciesRepository.findOne(Long.valueOf(speciesId));
    }

    @Override
    public void updateSpecies(Species species) throws VirtualShelterException {
        speciesRepository.edit(species);
    }

    @Override
    public List<Breed> listBreedsOfTheSpecies(long speciesId) throws VirtualShelterException {
        Species species = speciesRepository.findOne(Long.valueOf(speciesId));
        return species.getBreeds();
    }

    @Override
    public Animal findAnimalById(long animalId) throws VirtualShelterException {
        return animalRepository.findOne(Long.valueOf(animalId));
    }

    @Override
    public void updateAnimal(Animal animal) throws VirtualShelterException {
        animalRepository.edit(animal);
    }

    @Override
    public Advertiser getAdvertiserOfAnimal(long animalId) throws VirtualShelterException {
        return advertisementRepository.getAdvertiserOfAnimal(animalId);
    }

    @Override
    public List<Animal> listAnimalsAdvertisedByAdvertiser(long advertiserId) throws VirtualShelterException {
        return advertisementRepository.listAnimalsAdvertisedByAdvertiser(advertiserId);
    }

    @Override
    public List<Institution> listInstitutions() throws VirtualShelterException {
        return institutionRepository.findAll();
    }

    @Override
    public Institution getInstitutionById(long institutionId)
            throws VirtualShelterException {
        return institutionRepository.findOne(Long.valueOf(institutionId));
    }

    @Override
    public List<Picture> listInstitutionPictures(long institutionId)
            throws VirtualShelterException {
        Institution institution = institutionRepository.findOne(Long.valueOf(institutionId));
        return institution.getPicturesList();
    }

    @Override
    public Picture getInstitutionProfilePicture(long institutionId)
            throws VirtualShelterException {
        Institution institution = institutionRepository.findOne(Long.valueOf(institutionId));
        return institution.getProfilePicture();
    }

    @Override
    public void postNewProfilePictureForInstitution(long institutionId, Picture profilePicture)
            throws VirtualShelterException {
        Institution institution = institutionRepository.findOne(Long.valueOf(institutionId));
        institution.setProfilePicture(profilePicture);
    }

    @Override
    public void deleteProfilePictureForInstitution(long institutionId)
            throws VirtualShelterException {
        Institution institution = institutionRepository.findOne(Long.valueOf(institutionId));
        institution.setProfilePicture(null);
    }

    @Override
    public void postNewPictureForInstitution(long institutionId,
                                             Picture profilePicture) throws VirtualShelterException {
        Institution institution = institutionRepository.findOne(Long.valueOf(institutionId));
        List<Picture> pictureList = institution.getPicturesList();
        pictureList.add(profilePicture);
        institution.setPicturesList(pictureList);
    }

    @Override
    public void deletePictureForInstitution(long institutionId,
                                            Picture picture) throws VirtualShelterException {
        Institution institution = institutionRepository.findOne(Long.valueOf(institutionId));
        List<Picture> pictureList = institution.getPicturesList();
        pictureList.remove(picture);
        institution.setPicturesList(pictureList);

    }

    @Override
    public List<Picture> listUserPictures(long userId)
            throws VirtualShelterException {
        User user = userRepository.findOne(Long.valueOf(userId));
        return user.getPicturesList();
    }

    @Override
    public Picture getUserProfilePicture(long userId)
            throws VirtualShelterException {
        User user = userRepository.findOne(Long.valueOf(userId));
        return user.getProfilePicture();
    }

    @Override
    public void postNewProfilePictureForUser(long userId, Picture profilePicture)
            throws VirtualShelterException {
        User user = userRepository.findOne(Long.valueOf(userId));
        user.setProfilePicture(profilePicture);
    }

    @Override
    public void deleteProfilePictureForUser(long userId)
            throws VirtualShelterException {
        User user = userRepository.findOne(Long.valueOf(userId));
        user.setProfilePicture(null);

    }

    @Override
    public void postNewPictureForUser(long userId, Picture picture)
            throws VirtualShelterException {
        User user = userRepository.findOne(Long.valueOf(userId));
        List<Picture> pictures = user.getPicturesList();
        pictures.add(picture);
        user.setPicturesList(pictures);
    }

    @Override
    public void deletePictureForUser(long userId, Picture picture)
            throws VirtualShelterException {
        User user = userRepository.findOne(Long.valueOf(userId));
        List<Picture> pictures = user.getPicturesList();
        pictures.remove(picture);
        user.setPicturesList(pictures);
    }

    @Override
    public List<Picture> listAnimalPictures(long animalId)
            throws VirtualShelterException {
        Animal animal = animalRepository.findOne(Long.valueOf(animalId));
        return animal.getPicturesList();
    }

    @Override
    public void postNewPictureForAnimal(long animalId, Picture picture)
            throws VirtualShelterException {
        Animal animal = animalRepository.findOne(Long.valueOf(animalId));
        List<Picture> pictures = animal.getPicturesList();
        pictures.add(picture);
        animal.setPictureList(pictures);
    }

    @Override
    public Picture getAnimalProfilePicture(long animalId)
            throws VirtualShelterException {
        Animal animal = animalRepository.findOne(Long.valueOf(animalId));
        return animal.getProfilePicture();
    }

    @Override
    public void postNewProfilePictureForAnimal(long animalId,
                                               Picture profilePicture) throws VirtualShelterException {
        Animal animal = animalRepository.findOne(Long.valueOf(animalId));
        animal.setProfilePicture(profilePicture);
    }

    @Override
    public void deleteProfilePictureForAnimal(long animalId)
            throws VirtualShelterException {
        Animal animal = animalRepository.findOne(Long.valueOf(animalId));
        animal.setProfilePicture(null);

    }

    @Override
    public void deletePictureForAnimal(long animalId, Picture picture)
            throws VirtualShelterException {
        Animal animal = animalRepository.findOne(Long.valueOf(animalId));
        List<Picture> pictures = animal.getPicturesList();
        pictures.remove(picture);
        animal.setPictureList(pictures);
    }

    @Override
    public List<Animal> getAnimals() throws VirtualShelterException {
        return animalRepository.findAll();
    }

    @Override
    public Advertisement getAdvertisement(long id)
            throws VirtualShelterException {
        return advertisementRepository.findById(id);
    }

}
