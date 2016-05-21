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

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Rest controller class for the User resource.
 *
 * @author Kiss László
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserResourceAssembler userResourceAssembler;

    @Autowired
    private InstitutionResourceAssembler institutionResourceAssembler;

    @Autowired
    private ISiteAdministrationOperations siteAdministrationOperations;

    @Autowired
    private IAdvertisementOperations advertisementOperations;

    @Autowired
    private AnimalResourceAssembler animalResourceAssembler;

    @Autowired
    private PictureResourceAssembler pictureResourceAssembler;

    private VirtualShelterExceptionToHttpStatusConverter converter = new VirtualShelterExceptionToHttpStatusConverter();

    /**
     * Register new user.
     *
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<UserResource> addUser(@RequestBody @Valid User user) {
        HttpStatus responseStatus = HttpStatus.CREATED;
        try {
            this.advertisementOperations.registerUser(user);
        } catch (VirtualShelterException e) {
            responseStatus = converter.convert(e);
        }
        UserResource resource = userResourceAssembler.toResource(user);
        return new ResponseEntity<UserResource>(resource, responseStatus);
    }

    /**
     * List all users.
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<UserResource>> getAllUsers() {
        List<UserResource> resources = new ArrayList<UserResource>();
        HttpStatus responseStatus = HttpStatus.OK;
        try {
            List<User> users = this.siteAdministrationOperations.listAllUsers();
            resources.addAll(this.userResourceAssembler.toResources(users));
        } catch (VirtualShelterException e) {
            responseStatus = converter.convert(e);
        }
        return new ResponseEntity<List<UserResource>>(resources, responseStatus);
    }

    /**
     * Get one user by it's id.
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<UserResource> getUser(@PathVariable Long id) {
        UserResource resource = null;
        HttpStatus responseStatus = HttpStatus.OK;
        try {
            User user = this.siteAdministrationOperations.findUserById(id);
            if (user != null) {
                resource = this.userResourceAssembler.toResource(user);
            }
        } catch (VirtualShelterException e) {
            responseStatus = converter.convert(e);
        }
        return new ResponseEntity<UserResource>(resource, responseStatus);
    }

    /**
     * Update all values of user identified by id
     *
     * @param id
     * @param user
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity<UserResource> setUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id.intValue());
        HttpStatus responseStatus = HttpStatus.OK;
        try {
            this.advertisementOperations.updateUser(user);
        } catch (VirtualShelterException e) {
            responseStatus = converter.convert(e);
        }
        UserResource resource = this.userResourceAssembler.toResource(user);
        return new ResponseEntity<UserResource>(resource, responseStatus);
    }

    /**
     * Delete one user
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<UserResource> deleteUser(@PathVariable Long id) {
        HttpStatus responseStatus = HttpStatus.OK;
        try {
            this.advertisementOperations.deleteUser(id);
        } catch (VirtualShelterException e) {
            responseStatus = converter.convert(e);
        }

        return new ResponseEntity<UserResource>(responseStatus);
    }

    /**
     * Returns the institution owned by the user
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/institution", method = RequestMethod.GET)
    ResponseEntity<List<InstitutionResource>> getUsersInstitution(@PathVariable Long id) {
        List<InstitutionResource> resources = new ArrayList<InstitutionResource>();
        HttpStatus responseStatus = HttpStatus.OK;
        try {
            List<Institution> institutionList = this.siteAdministrationOperations.listInstituitionsOwnedByUser(id);
            resources.addAll(this.institutionResourceAssembler.toResources(institutionList));
        } catch (VirtualShelterException e) {
            responseStatus = converter.convert(e);
        }
        return new ResponseEntity<List<InstitutionResource>>(resources, responseStatus);
    }

    @RequestMapping(value = "/{id}/adminInstitution", method = RequestMethod.GET)
    ResponseEntity<List<InstitutionResource>> getUsersAdministrableInstitution(@PathVariable Long id) {
        List<InstitutionResource> resources = new ArrayList<>();
        HttpStatus responseStatus = HttpStatus.OK;
        try {
            List<Institution> institutionList = this.siteAdministrationOperations.listInstitutionsAdministeredByUser(id);
            resources.addAll(this.institutionResourceAssembler.toResources(institutionList));
        } catch (VirtualShelterException e) {
            responseStatus = converter.convert(e);
        }
        return new ResponseEntity<>(resources, responseStatus);
    }

    /**
     * Create new institution,
     * the user will be the owner
     *
     * @param id
     * @param institution
     * @return
     */
    @RequestMapping(value = "/{id}/institution", method = RequestMethod.POST)
    ResponseEntity<InstitutionResource> createNewInstitution(@PathVariable Long id, @RequestBody Institution institution) {


        HttpStatus responseStatus = HttpStatus.CREATED;
        try {
            User user = this.siteAdministrationOperations.findUserById(id);
            institution.setOwner(user);
            this.advertisementOperations.registerInstitution(institution);
        } catch (VirtualShelterException e) {
            responseStatus = converter.convert(e);
        }
        InstitutionResource resource = this.institutionResourceAssembler.toResource(institution);
        return new ResponseEntity<InstitutionResource>(resource, responseStatus);
    }

    /**
     * Make the user the new owner of the institution
     *
     * @param id
     * @param institution
     */
    @RequestMapping(value = "/{id}/institution", method = RequestMethod.PUT)
    ResponseEntity<InstitutionResource> makeOwnerOfInstitution(@PathVariable Long id, @RequestBody Institution institution) {
        HttpStatus responseStatus = HttpStatus.OK;
        try {
            this.advertisementOperations.changeInstitutionOwner(id, institution.getId());
        } catch (VirtualShelterException e) {
            responseStatus = converter.convert(e);
        }
        return new ResponseEntity<InstitutionResource>(responseStatus);
    }

    /**
     * Take the ownership of the institution from the user
     *
     * @param id
     * @param institution
     */
    @RequestMapping(value = "/{id}/institution", method = RequestMethod.DELETE)
    void takeOwnerOfInstitution(@PathVariable Long id, @RequestBody Institution institution) {
        //TODO ?
    }

    /**
     * List the advertised animals of the user
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/advertisement", method = RequestMethod.GET)
    ResponseEntity<List<AnimalResource>> getUsersAdvertisements(@PathVariable Long id) {
        List<AnimalResource> resources = new ArrayList<AnimalResource>();
        HttpStatus responseStatus = HttpStatus.OK;
        try {
            List<Advertisement> advertisementList = this.advertisementOperations.listAdvertisementsFromAdvertiser(id);
            for (Advertisement advertisement : advertisementList) {
                resources.add(this.animalResourceAssembler.toResource(advertisement.getAnimal()));
            }
        } catch (VirtualShelterException e) {
            responseStatus = converter.convert(e);
        }
        return new ResponseEntity<List<AnimalResource>>(resources, responseStatus);
    }

    /**
     * Post new animal as an advertisement in the name of
     * the user
     *
     * @param id
     * @param animal
     * @return
     */
    @RequestMapping(value = "/{id}/advertisement", method = RequestMethod.POST)
    ResponseEntity<AnimalResource> createNewAdvertisement(@PathVariable Long id, @RequestBody Animal animal) {
        HttpStatus responseStatus = HttpStatus.OK;
        Advertisement advertisement = new Advertisement();
        advertisement.setAnimal(animal);
        try {
            User user = this.siteAdministrationOperations.findUserById(id);
            advertisement.setAdvertiser(user);
            this.advertisementOperations.createAdvertisement(advertisement);
            this.advertisementOperations.advertise(advertisement.getId());
        } catch (VirtualShelterException e) {
            responseStatus = converter.convert(e);
        }
        AnimalResource resource = this.animalResourceAssembler.toResource(advertisement.getAnimal());
        return new ResponseEntity<AnimalResource>(resource, responseStatus);
    }

    /**
     * List pictures of the user
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/pictures", method = RequestMethod.GET)
    ResponseEntity<List<PictureResource>> getUsersPictures(@PathVariable Long id) {
        //TODO
        return null;
    }

    /**
     * Post new picture of the user
     *
     * @param id
     * @param picture
     * @return
     */
    @RequestMapping(value = "/{id}/pictures", method = RequestMethod.POST)
    ResponseEntity<PictureResource> createUsersPicture(@PathVariable Long id, @RequestBody Picture picture) {
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
     * True if the user is siteadmin
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/admin", method = RequestMethod.GET)
    ResponseEntity<Boolean> isUserSiteAdmin(@PathVariable Long id) {
        Boolean responseValue = Boolean.FALSE;
        HttpStatus responseStatus = HttpStatus.OK;
        try {
            responseValue = this.siteAdministrationOperations.isUserSiteAdministrator(id);
        } catch (VirtualShelterException e) {
            responseStatus = converter.convert(e);
        }
        return new ResponseEntity<Boolean>(responseValue, responseStatus);
    }

    /**
     * Make the user siteadmin
     *
     * @param id
     */
    @RequestMapping(value = "/{id}/admin", method = RequestMethod.POST)
    ResponseEntity<UserResource> setUserSiteAdmin(@PathVariable Long id) {
        HttpStatus responseStatus = HttpStatus.OK;
        try {
            this.siteAdministrationOperations.promoteSiteAdministrator(id);
        } catch (VirtualShelterException e) {
            responseStatus = converter.convert(e);
        }
        return new ResponseEntity<UserResource>(responseStatus);
    }

    /**
     * Revoke the user from siteadmins
     *
     * @param id
     */
    @RequestMapping(value = "/{id}/admin", method = RequestMethod.DELETE)
    ResponseEntity<UserResource> deleteUserSiteAdmin(@PathVariable Long id) {
        HttpStatus responseStatus = HttpStatus.OK;
        try {
            this.siteAdministrationOperations.revokeSiteAdministrator(id);
        } catch (VirtualShelterException e) {
            responseStatus = converter.convert(e);
        }
        return new ResponseEntity<UserResource>(responseStatus);
    }

    /**
     * Returns the user's profile picture
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/profile", method = RequestMethod.GET)
    ResponseEntity<PictureResource> getUsersProfilePicture(@PathVariable Long id) {
        //TODO
        return null;
    }

    /**
     * Post new profile picture for the user
     *
     * @param id
     * @param picture
     * @return
     */
    @RequestMapping(value = "/{id}/profile", method = RequestMethod.POST)
    ResponseEntity<PictureResource> createUsersProfilePicture(@PathVariable Long id, @RequestBody Picture picture) {
        //TODO
        return null;
    }

    /**
     * Delete the user's profile picture
     *
     * @param id
     */
    @RequestMapping(value = "/{id}/profile", method = RequestMethod.DELETE)
    void deleteUsersProfilePicture(@PathVariable Long id) {
        //TODO
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }

    @RequestMapping(value = "/administeredShelter", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<InstitutionResource>> currentUsersAdministeredInstitution(Principal principal) {
        if (principal == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        final List<InstitutionResource> resources = new ArrayList<>();
        HttpStatus responseStatus = HttpStatus.OK;
        try {
            final User user = siteAdministrationOperations.findUserByEmail(principal.getName());
            final List<Institution> institutionList = this.siteAdministrationOperations
                    .listInstitutionsAdministeredByUser(user.getId());
            resources.addAll(this.institutionResourceAssembler.toResources(institutionList));
        } catch (VirtualShelterException e) {
            responseStatus = converter.convert(e);
        }
        return new ResponseEntity<>(resources, responseStatus);
    }

}
