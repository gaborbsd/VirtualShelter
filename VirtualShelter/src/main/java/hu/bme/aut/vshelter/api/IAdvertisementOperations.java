package hu.bme.aut.vshelter.api;

import hu.bme.aut.vshelter.entity.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface IAdvertisementOperations {

    /**
     * Advertises the given advertisement in the name of the given institution
     *
     * @param instituionID    the creator's id of the advertisement
     * @param advertisementID the id of the advertisement to advertise, which includes the detail of the advertisement
     * @throws VirtualShelterException
     */
    public void advertise(long instituionID, long advertisementID) throws VirtualShelterException;

    /**
     * Advertises the given advertisement in the name of the logged in user
     *
     * @param advertisementId the id of the advertisement to advertise, which includes the detail of the advertisement
     * @throws VirtualShelterException
     */
    public void advertise(long advertisementId) throws VirtualShelterException;

    /**
     * Registers a user if there is no other user with the given e-mail address
     *
     * @param user contains the user's data to be registered
     * @throws VirtualShelterException
     */
    public void registerUser(User user) throws VirtualShelterException;

    /**
     * Deletes the user from the database with the given userId
     *
     * @param userId the user with that id will be deleted from the database
     * @throws VirtualShelterException
     */
    public void deleteUser(long userId) throws VirtualShelterException;

    /**
     * Updates the user in the database with the given User's data
     *
     * @param user user contains the user's data to be updated
     * @throws VirtualShelterException
     */
    public void updateUser(User user) throws VirtualShelterException;

    /**
     * Uploads a picture to a user account (for the given advertiser)
     *
     * @param picture      picture to be uploaded
     * @param advertiserId for this user/institution (advertiser) will be the picture uploaded
     * @throws VirtualShelterException
     */
    public void uploadPicture(Picture picture, long advertiserId) throws VirtualShelterException;

    /**
     * Deletes a picture from a user/institution account (from the given advertiser)
     *
     * @param pictureId    picture's id to be deleted
     * @param advertiserId for this user/institution (advertiser) will be the picture deleted
     * @throws VirtualShelterException
     */
    public void deletePicture(long pictureId, long advertiserId) throws VirtualShelterException;

    /**
     * Creates an institution if there is no other institution with the given e-mail address
     *
     * @param institution contains the institution's data to be registered
     * @throws VirtualShelterException
     */
    @PreAuthorize("isAuthenticated()")
    public void registerInstitution(Institution institution) throws VirtualShelterException;


    /**
     * Deletes the institution from the database with the given instituionID
     *
     * @param institutionId the institution with that id will be deleted from the database
     * @throws VirtualShelterException
     */
    public void deleteInstitution(long institutionId) throws VirtualShelterException;

    /**
     * Updates the institution in the database with the given instituiton's data
     *
     * @param institution contains the institution's data to be updated
     * @throws VirtualShelterException
     */
    public void updateInstitution(Institution institution) throws VirtualShelterException;

    /**
     * Changes the given instituion's owner
     *
     * @param userId        the new owner
     * @param institutionId the owner of this instituion's will be changed
     * @throws VirtualShelterException
     */
    public void changeInstitutionOwner(long userId, long institutionId) throws VirtualShelterException;

    /**
     * Adds to the given institution a new administrator
     *
     * @param userId        the new institution administrator
     * @param institutionId this institution will get a new administrator
     * @throws VirtualShelterException
     */
    public void addInstitutionAdministrator(long userId, long institutionId) throws VirtualShelterException;

    /**
     * Deletes the administrator with the given userID from the institution with the given instituionID
     *
     * @param userId        the deleted institution administrator's userID
     * @param institutionId from the institution with that id will be the administrator deleted
     * @throws VirtualShelterException
     */
    public void deleteInstitutionAdministrator(long userId, long institutionId) throws VirtualShelterException;


    /**
     * Returns the institution administrators for the institution with the given id
     *
     * @param institutionId
     * @return
     */
    public List<User> listInstitutionAdministrators(long institutionId) throws VirtualShelterException;

    /**
     * Creates an advertisement
     *
     * @param advertisement contains the advertisement's data to be created
     * @throws VirtualShelterException
     */
    public void createAdvertisement(Advertisement advertisement) throws VirtualShelterException;

    /**
     * Deletes an advertisement with the given advertisementId
     *
     * @param advertisementId the advertisement with this advertisementId will be deleted from the database
     * @throws VirtualShelterException
     */
    public void deleteAdvertisement(long advertisementId) throws VirtualShelterException;

    /**
     * Returns a list of Advertisements which are satisfied the conditions
     *
     * @param aqf Contains the search's conditions
     * @return
     * @throws VirtualShelterException
     */
    public List<Advertisement> searchAdvertisements(AdvertisementQueryFilter aqf) throws VirtualShelterException;

    /**
     * Returns with all of the advertisements
     *
     * @return
     * @throws VirtualShelterException
     */
    public List<Advertisement> listAllAdvertisements() throws VirtualShelterException;

    /**
     * Returns all of the species
     *
     * @return
     * @throws VirtualShelterException
     */
    public List<Species> listAllSpecies() throws VirtualShelterException;

    /**
     * Returns all of the breeds
     *
     * @return
     * @throws VirtualShelterException
     */
    public List<Breed> listAllBreeds() throws VirtualShelterException;

    /**
     * Add the given animal to the database
     *
     * @param Animal object with the attributes of the animal
     * @throws VirtualShelterException
     */
    @PreAuthorize("isAuthenticated()")
    public void addAnimal(Animal animal) throws VirtualShelterException;

    /**
     * Deletes the animal from the database with the given animalId
     *
     * @param animalId the animal with that id will be deleted from the database
     * @throws VirtualShelterException
     */
    public void deleteAnimal(long animalId) throws VirtualShelterException;

    /**
     * Returns the advertisements of the advertiser(user or institution)
     *
     * @param advertiserId
     * @return
     */
    public List<Advertisement> listAdvertisementsFromAdvertiser(long advertiserId) throws VirtualShelterException;

    /**
     * Returns the species by the given speciesId
     *
     * @param speciesId
     * @return
     */
    public Species findSpeciesById(long speciesId) throws VirtualShelterException;

    /**
     * Updates the species identified by given species' id
     *
     * @param species
     */
    public void updateSpecies(Species species) throws VirtualShelterException;

    /**
     * list all breeds of the given speciesId
     *
     * @param speciesId
     */
    public List<Breed> listBreedsOfTheSpecies(long speciesId) throws VirtualShelterException;

    /**
     * Returns the animal identified by the given animalId
     *
     * @param animalID
     * @return
     */
    public Animal findAnimalById(long animalId) throws VirtualShelterException;

    /**
     * Updates the animal identified by the given animal's id
     *
     * @param animal
     */
    public void updateAnimal(Animal animal) throws VirtualShelterException;

    /**
     * Returns the Advertiser of the Animal with the given animalId
     *
     * @param animalId
     * @return
     */
    public Advertiser getAdvertiserOfAnimal(long animalId) throws VirtualShelterException;

    /**
     * Lists the animals advertised by the advertiser with the given advertiserId:
     *
     * @param advertiserId
     * @return
     */
    public List<Animal> listAnimalsAdvertisedByAdvertiser(long advertiserId) throws VirtualShelterException;

    /**
     * Returns the Institutions
     *
     * @return
     */
    public List<Institution> listInstitutions() throws VirtualShelterException;

    /**
     * Returns the Institution with the given ID.
     *
     * @param institutionId
     * @return
     * @throws VirtualShelterException
     */
    public Institution getInstitutionById(long institutionId) throws VirtualShelterException;

    /**
     * Returns the pictures of the institution with the given institutionId
     *
     * @param institutionId
     * @return
     */
    public List<Picture> listInstitutionPictures(long institutionId) throws VirtualShelterException;

    /**
     * Returns the profile picture of the institution with the given institutionId
     *
     * @return
     * @throws VirtualShelterException
     */
    public Picture getInstitutionProfilePicture(long institutionId) throws VirtualShelterException;

    /**
     * Posts a new profile picture for institution with the given institutionId
     *
     * @param institutionId
     * @throws VirtualShelterException
     */
    public void postNewProfilePictureForInstitution(long institutionId, Picture profilePicture) throws VirtualShelterException;

    /**
     * Deletes the profilePicture for the institution with the given institutionId
     *
     * @param institutionId
     * @throws VirtualShelterException
     */
    public void deleteProfilePictureForInstitution(long institutionId) throws VirtualShelterException;

    /**
     * Posts a new picture for institution with the given institutionId
     *
     * @param institutionId
     * @param profilePicture
     * @throws VirtualShelterException
     */
    public void postNewPictureForInstitution(long institutionId, Picture profilePicture) throws VirtualShelterException;

    /**
     * Deletes the given Picture for the institution with the given institutionId
     *
     * @param institutionId
     * @param profilePicture
     * @throws VirtualShelterException
     */
    public void deletePictureForInstitution(long institutionId, Picture picture) throws VirtualShelterException;

    /**
     * List all pictures that belongs to the user identified by the given id
     *
     * @param userId
     * @return
     * @throws VirtualShelterException
     */
    public List<Picture> listUserPictures(long userId) throws VirtualShelterException;

    /**
     * Returns the profile picture of the user identified by the given id
     *
     * @param userId
     * @return
     * @throws VirtualShelterException
     */
    public Picture getUserProfilePicture(long userId) throws VirtualShelterException;

    /**
     * Change the profile picture of the user identified by the given id.
     * The previous profile picture will be deleted.
     *
     * @param userId
     * @param profilePicture
     * @throws VirtualShelterException
     */
    public void postNewProfilePictureForUser(long userId, Picture profilePicture) throws VirtualShelterException;

    /**
     * Delete the profile picture of the user identified by the given id.
     *
     * @param userId
     * @throws VirtualShelterException
     */
    public void deleteProfilePictureForUser(long userId) throws VirtualShelterException;

    /**
     * Add a new picture to the picture list of the user identified by the given id.
     *
     * @param userId
     * @param picture
     * @throws VirtualShelterException
     */
    public void postNewPictureForUser(long userId, Picture picture) throws VirtualShelterException;

    /**
     * Delete the picture with the given picture id from the picture list of the user identified by the given user id.
     *
     * @param userId
     * @param picture
     * @throws VirtualShelterException
     */
    public void deletePictureForUser(long userId, Picture picture) throws VirtualShelterException;

    /**
     * List all pictures that belongs to the animal identified by the given id
     *
     * @param animalId
     * @return
     * @throws VirtualShelterException
     */
    public List<Picture> listAnimalPictures(long animalId) throws VirtualShelterException;

    /**
     * Add a new picture to the picture list of the animal identified by the given id.
     *
     * @param animalId
     * @param picture
     * @throws VirtualShelterException
     */
    public void postNewPictureForAnimal(long animalId, Picture picture) throws VirtualShelterException;

    /**
     * Return the profile picture of the animal identified by the given id
     *
     * @param animalId
     * @return
     * @throws VirtualShelterException
     */
    public Picture getAnimalProfilePicture(long animalId) throws VirtualShelterException;

    /**
     * Change the profile picture of the user identified by the given id.
     * The previous profile picture will be deleted.
     *
     * @param animalId
     * @param profilePicture
     * @throws VirtualShelterException
     */
    public void postNewProfilePictureForAnimal(long animalId, Picture profilePicture) throws VirtualShelterException;

    /**
     * Delete the profile picture of the animal identified by the given id.
     *
     * @param animalId
     * @throws VirtualShelterException
     */
    public void deleteProfilePictureForAnimal(long animalId) throws VirtualShelterException;

    /**
     * Delete the picture with the given picture id from the picture list of the animal identified by the given animal id.
     *
     * @param animalId
     * @param picture
     * @throws VirtualShelterException
     */
    public void deletePictureForAnimal(long animalId, Picture picture) throws VirtualShelterException;

    /**
     * List all animal in the database
     *
     * @return
     * @throws VirtualShelterException
     */
    public List<Animal> getAnimals() throws VirtualShelterException;

    /**
     * Returns the advertisement identified by the given id
     *
     * @param advertisementId
     * @return
     * @throws VirtualShelterException
     */
    public Advertisement getAdvertisement(long id) throws VirtualShelterException;
}
