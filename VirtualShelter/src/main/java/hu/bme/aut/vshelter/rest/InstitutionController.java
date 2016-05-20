package hu.bme.aut.vshelter.rest;

import hu.bme.aut.vshelter.api.IAdvertisementOperations;
import hu.bme.aut.vshelter.api.ISiteAdministrationOperations;
import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.entity.*;
import hu.bme.aut.vshelter.rest.resources.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Rest controller class for the Institution resource
 *
 * @author Kiss László
 */
@RestController
@RequestMapping(value = "/shelter")
public class InstitutionController {

    @Autowired
    private InstitutionResourceAssembler institutionResourceAssembler;

    @Autowired
    private ISiteAdministrationOperations siteAdministationOperations;

    @Autowired
    private IAdvertisementOperations advertisementOperations;

    @Autowired
    private UserResourceAssembler userResourceAssembler;

    @Autowired
    private AnimalResourceAssembler animalResourceAssembler;

    @Autowired
    private PictureResourceAssembler pictureResourceAssembler;

    private VirtualShelterExceptionToHttpStatusConverter converter = new VirtualShelterExceptionToHttpStatusConverter();

    /**
     * Create new institution
     *
     * @param institution
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<InstitutionResource> addInstitution(@RequestBody Institution institution, Principal principal) {
        if (principal == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        HttpStatus responseStatus = HttpStatus.CREATED;
        try {
            final String userEmail = principal.getName();
            final User user = siteAdministationOperations.findUserByEmail(userEmail);
            institution.setOwner(user);
            this.advertisementOperations.registerInstitution(institution);
        } catch (VirtualShelterException e) {
            responseStatus = converter.convert(e);
        }
        InstitutionResource resource = this.institutionResourceAssembler.toResource(institution);
        return new ResponseEntity<InstitutionResource>(resource, responseStatus);
    }

    /**
     * List all institutions
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<InstitutionResource>> getAllInstitutions() {
        HttpStatus responseStatus = HttpStatus.OK;
        List<InstitutionResource> resources = new ArrayList<InstitutionResource>();
        try {
            List<Institution> institutionList = this.advertisementOperations.listInstitutions();
            resources = this.institutionResourceAssembler.toResources(institutionList);
        } catch (VirtualShelterException e) {
            responseStatus = this.converter.convert(e);
        }
        return new ResponseEntity<List<InstitutionResource>>(resources, responseStatus);
    }

    /**
     * Find one institution by id
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<InstitutionResource> getInstitution(@PathVariable Long id) {
        InstitutionResource resource = null;
        HttpStatus responseStatus = HttpStatus.OK;
        try {
            Institution institution = this.advertisementOperations.getInstitutionById(id);
            resource = this.institutionResourceAssembler.toResource(institution);
        } catch (VirtualShelterException e) {
            responseStatus = this.converter.convert(e);
        }
        return new ResponseEntity<InstitutionResource>(resource, responseStatus);
    }

    /**
     * Update the institution
     *
     * @param id
     * @param institution
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity<InstitutionResource> setInstitution(@PathVariable Long id, @RequestBody Institution institution) {
        institution.setId(id);
        InstitutionResource resource = this.institutionResourceAssembler.toResource(institution);
        HttpStatus responseStatus = HttpStatus.OK;
        try {
            this.advertisementOperations.updateInstitution(institution);
        } catch (VirtualShelterException e) {
            responseStatus = converter.convert(e);
        }

        return new ResponseEntity<InstitutionResource>(resource, responseStatus);
    }

    /**
     * Delete the institution
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<InstitutionResource> deleteInstitution(@PathVariable Long id) {
        HttpStatus responseStatus = HttpStatus.OK;
        try {
            this.advertisementOperations.deleteInstitution(id);
        } catch (VirtualShelterException e) {
            responseStatus = converter.convert(e);
        }
        return new ResponseEntity<InstitutionResource>(responseStatus);
    }

    /**
     * List the admins of the institution
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/admin", method = RequestMethod.GET)
    ResponseEntity<List<UserResource>> getAdmins(@PathVariable Long id) {
        List<UserResource> resources = new ArrayList<UserResource>();
        HttpStatus responseStatus = HttpStatus.OK;
        try {
            List<User> userList = this.advertisementOperations.listInstitutionAdministrators(id);
            resources.addAll(this.userResourceAssembler.toResources(userList));
        } catch (VirtualShelterException e) {
            responseStatus = converter.convert(e);
        }
        return new ResponseEntity<List<UserResource>>(resources, responseStatus);
    }

    /**
     * Make the user admin of the institution
     *
     * @param id
     * @param user
     */
    @RequestMapping(value = "/{id}/admin", method = RequestMethod.POST)
    ResponseEntity<InstitutionResource> postAdmin(@PathVariable Long id, @RequestBody User user) {
        HttpStatus responseStatus = HttpStatus.OK;
        try {
            this.advertisementOperations.addInstitutionAdministrator(user.getId(), id);
        } catch (VirtualShelterException e) {
            responseStatus = converter.convert(e);
        }
        return new ResponseEntity<InstitutionResource>(responseStatus);
    }

    /**
     * Delete the given user from the institution's admins
     *
     * @param id
     * @param user
     */
    @RequestMapping(value = "/{id}/admin", method = RequestMethod.PUT)
    ResponseEntity<InstitutionResource> deleteAdmin(@PathVariable Long id, @RequestBody User user) {
        HttpStatus responseStatus = HttpStatus.OK;
        try {
            this.advertisementOperations.deleteInstitutionAdministrator(user.getId(), id);
        } catch (VirtualShelterException e) {
            responseStatus = converter.convert(e);
        }
        return new ResponseEntity<InstitutionResource>(responseStatus);
    }

    /**
     * List the animals advertised by the institution
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/advertisement", method = RequestMethod.GET)
    ResponseEntity<List<AnimalResource>> getInstitutionsAdvertisements(@PathVariable Long id) {
        HttpStatus responseStatus = HttpStatus.OK;
        List<AnimalResource> resources = new ArrayList<AnimalResource>();
        try {
            List<Animal> animalList = this.advertisementOperations.listAnimalsAdvertisedByAdvertiser(id);
            resources.addAll(this.animalResourceAssembler.toResources(animalList));
        } catch (VirtualShelterException e) {
            responseStatus = converter.convert(e);
        }
        return new ResponseEntity<List<AnimalResource>>(resources, responseStatus);
    }

    /**
     * Advertise the animal in the name of the institution
     *
     * @param id
     * @param animal
     * @return
     */
    @RequestMapping(value = "/{id}/advertisement", method = RequestMethod.POST)
    ResponseEntity<AnimalResource> createNewAdvertisement(@PathVariable Long id, @RequestBody Animal animal) {
        Advertisement advertisement = new Advertisement();
        advertisement.setAnimal(animal);
        HttpStatus responseStatus = HttpStatus.CREATED;
        try {
            Institution institution = this.advertisementOperations.getInstitutionById(id);
            advertisement.setAdvertiser(institution);
            this.advertisementOperations.createAdvertisement(advertisement);
            this.advertisementOperations.advertise(advertisement.getId());
        } catch (VirtualShelterException e) {
            responseStatus = this.converter.convert(e);
        }
        AnimalResource resource = this.animalResourceAssembler.toResource(advertisement.getAnimal());
        return new ResponseEntity<AnimalResource>(resource, responseStatus);
    }

    /**
     * List all pictures of the institution
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/pictures", method = RequestMethod.GET)
    ResponseEntity<List<PictureResource>> getInstitutionsPictures(@PathVariable Long id) {
        //TODO
        return null;
    }

    /**
     * Post new picture of the institution
     *
     * @param id
     * @param picture
     * @return
     */
    @RequestMapping(value = "/{id}/pictures", method = RequestMethod.POST)
    ResponseEntity<PictureResource> createInstitutionsPicture(@PathVariable Long id, @RequestBody Picture picture) {
        HttpStatus responseStatus = HttpStatus.CREATED;
        try {
            this.advertisementOperations.uploadPicture(picture, id);
        } catch (VirtualShelterException e) {
            responseStatus = converter.convert(e);
        }
        PictureResource resource = this.pictureResourceAssembler.toResource(picture);
        return new ResponseEntity<PictureResource>(resource, responseStatus);
    }

    /**
     * Returns the profile picture of the institution
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/profile", method = RequestMethod.GET)
    ResponseEntity<PictureResource> getInstitutionsProfilePicture(@PathVariable Long id) {
        //TODO
        return null;
    }

    /**
     * Post new profile picture for the institution
     *
     * @param id
     * @param picture
     * @return
     */
    @RequestMapping(value = "/{id}/profile", method = RequestMethod.POST)
    ResponseEntity<PictureResource> createInstitutionsProfilePicture(@PathVariable Long id, @RequestBody Picture picture) {
        //TODO
        return null;
    }

    /**
     * Delete profile picture of the institution
     *
     * @param id
     */
    @RequestMapping(value = "/{id}/profile", method = RequestMethod.DELETE)
    void deleteInstitutionsProfilePicture(@PathVariable Long id) {
        //TODO
    }

    /**
     * Returns the owner of the institution
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/owner", method = RequestMethod.GET)
    ResponseEntity<UserResource> getOwner(@PathVariable Long id) {
        HttpStatus responseStatus = HttpStatus.OK;
        UserResource resource = null;
        try {
            User user = this.siteAdministationOperations.findOwnerOfInstitution(id);
            resource = this.userResourceAssembler.toResource(user);
        } catch (VirtualShelterException e) {
            responseStatus = converter.convert(e);
        }
        return new ResponseEntity<UserResource>(resource, responseStatus);
    }
}
