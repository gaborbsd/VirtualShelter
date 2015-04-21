package hu.bme.aut.vshelter.rest;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.vshelter.api.IAdvertisementOperations;
import hu.bme.aut.vshelter.api.ISiteAdministrationOperations;
import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.entity.Advertisement;
import hu.bme.aut.vshelter.entity.Animal;
import hu.bme.aut.vshelter.entity.Institution;
import hu.bme.aut.vshelter.entity.Picture;
import hu.bme.aut.vshelter.entity.User;
import hu.bme.aut.vshelter.rest.resources.AnimalResource;
import hu.bme.aut.vshelter.rest.resources.InstitutionResource;
import hu.bme.aut.vshelter.rest.resources.InstitutionResourceAssembler;
import hu.bme.aut.vshelter.rest.resources.PictureResource;
import hu.bme.aut.vshelter.rest.resources.UserResource;
import hu.bme.aut.vshelter.rest.resources.UserResourceAssembler;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller class for the User resource.
 * 
 * @author Kiss László
 *
 */
@RestController
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private UserResourceAssembler userResourceAssembler;
	
	@Autowired
	private InstitutionResourceAssembler institutionResourceAssembler;
	
	@Autowired
	private ISiteAdministrationOperations siteAdministrationOperations;
	
	@Autowired
	private IAdvertisementOperations advertisementOperations;
	
	
	/**
	 * Register new user.
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	ResponseEntity<UserResource> addUser(@RequestBody User user) {
		
		UserResource resource = userResourceAssembler.toResource(user);
		
		
		HttpStatus responseStatus = HttpStatus.CREATED;
		try {
			this.advertisementOperations.registerUser(user);
		} catch (VirtualShelterException e) {
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<UserResource>(resource, responseStatus);
	}
	
	/**
	 * List all users.
	 * 
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<UserResource>> getAllUsers() {
		List<UserResource> resources = new ArrayList<UserResource>();
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			List<User> users = this.siteAdministrationOperations.listAllUsers();
			for (User user : users) {
				UserResource newResource = this.userResourceAssembler.toResource(user);
				newResource.add(linkTo(UserController.class).slash(user.getId()).withSelfRel());
				newResource.add(linkTo(UserController.class).slash(user.getId()).slash("institution").withRel("institution"));
				newResource.add(linkTo(UserController.class).slash(user.getId()).slash("advertisement").withRel("advertisement"));
				newResource.add(linkTo(UserController.class).slash(user.getId()).slash("picture").withRel("picture"));
				newResource.add(linkTo(UserController.class).slash(user.getId()).slash("admin").withRel("admin"));
				newResource.add(linkTo(UserController.class).slash(user.getId()).slash("profile").withRel("profile"));
				resources.add(newResource);
			}
		} catch (VirtualShelterException e) {
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<UserResource>>(resources, responseStatus);
	}
	
	/**
	 * Get one user by it's id.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<UserResource> getUser(@PathVariable Long id) {
		//TODO
		return null;
	}
	
	/**
	 * Update all values of user identified by id 
	 * 
	 * @param id
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	ResponseEntity<UserResource> setUser(@PathVariable Long id, @RequestBody User user) {
		user.setId(id.intValue());
		UserResource resource = this.userResourceAssembler.toResource(user);
		HttpStatus responseStatus = HttpStatus.OK;
		try {
			this.advertisementOperations.updateUser(user);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
		}
		return new ResponseEntity<UserResource>(resource, responseStatus);
	}
	
	/**
	 * Delete one user
	 * 
	 * @param id
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	void deleteUser(@PathVariable Long id) {
		try {
			this.advertisementOperations.deleteUser(id);
		} catch (VirtualShelterException e) {
			// TODO
		}
	}
	
	/**
	 * Returns the institution owned by the user
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/institution", method=RequestMethod.GET)
	ResponseEntity<InstitutionResource> getUsersInstitution(@PathVariable Long id) {
		//TODO
		return null;
	}
	
	/**
	 * Create new institution, 
	 * the user will be the owner
	 * 
	 * @param id
	 * @param institution
	 * @return
	 */
	@RequestMapping(value="/{id}/institution", method=RequestMethod.POST)
	ResponseEntity<InstitutionResource> createNewInstitution(@PathVariable Long id, @RequestBody Institution institution) {
		
		InstitutionResource resource = this.institutionResourceAssembler.toResource(institution);
		HttpStatus responseStatus = HttpStatus.CREATED;
		try {
			this.advertisementOperations.registerInstitution(institution);
			//TODO make {id} user the owner
		} catch (VirtualShelterException e) {
			responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<InstitutionResource>(resource, responseStatus);
	}
	
	/**
	 * Make the user the new owner of the institution
	 * 
	 * @param id
	 * @param institution
	 */
	@RequestMapping(value="/{id}/institution", method=RequestMethod.PUT)
	void makeOwnerOfInstitution(@PathVariable Long id, @RequestBody Institution institution) {
		try {
			this.advertisementOperations.changeInstitutionOwner(id, institution.getId());
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	/**
	 * Take the ownership of the institution from the user
	 * 
	 * @param id
	 * @param institution
	 */
	@RequestMapping(value="/{id}/institution", method=RequestMethod.DELETE)
	void takeOwnerOfInstitution(@PathVariable Long id, @RequestBody Institution institution) {
		//TODO ?
	}
	
	/**
	 * List the advertised animals of the user
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/advertisement", method=RequestMethod.GET)
	ResponseEntity<List<AnimalResource>> getUsersAdvertisements(@PathVariable Long id) {
		//TODO
		return null;
	}
	
	/**
	 * Post new animal as an advertisement in the name of
	 * the user
	 * 
	 * @param id
	 * @param animal
	 * @return
	 */
	@RequestMapping(value="/{id}/advertisement", method=RequestMethod.POST)
	ResponseEntity<AnimalResource> createNewAdvertisement(@PathVariable Long id, @RequestBody Animal animal) {
		Advertisement advertisement = new Advertisement();
		advertisement.setAnimal(animal);
		try {
			this.advertisementOperations.createAdvertisement(advertisement);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			this.advertisementOperations.advertise(advertisement.getId());
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * List pictures of the user
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/pictures", method=RequestMethod.GET)
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
	@RequestMapping(value="/{id}/pictures", method=RequestMethod.POST)
	ResponseEntity<PictureResource> createUsersPicture(@PathVariable Long id, @RequestBody Picture picture) {
		
		try {
			//Visszatérési értékben picutre kéne
			this.advertisementOperations.uploadPicture(picture, id);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * True if the user is siteadmin
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/admin", method=RequestMethod.GET)
	ResponseEntity<Boolean> isUserSiteAdmin(@PathVariable Long id) {
		//TODO 
		return null;
	}
	
	/**
	 * Make the user siteadmin
	 * 
	 * @param id
	 */
	@RequestMapping(value="/{id}/admin", method=RequestMethod.POST)
	void setUserSiteAdmin(@PathVariable Long id) {
		
		try {
			this.siteAdministrationOperations.promoteSiteAdministrator(id);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
		}
	
	}
	
	/**
	 * Revoke the user from siteadmins
	 * 
	 * @param id
	 */
	@RequestMapping(value="/{id}/admin", method=RequestMethod.DELETE)
	void deleteUserSiteAdmin(@PathVariable Long id) {

		try {
			this.siteAdministrationOperations.revokeSiteAdministrator(id);
		} catch (VirtualShelterException e) {
			// TODO Auto-generated catch block
		}
		
	}
	
	/**
	 * Returns the user's profile picture
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/profile", method=RequestMethod.GET)
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
	@RequestMapping(value="/{id}/profile", method=RequestMethod.POST)
	ResponseEntity<PictureResource> createUsersProfilePicture(@PathVariable Long id, @RequestBody Picture picture) {
		//TODO
		return null;
	}
	
	/**
	 * Delete the user's profile picture
	 * 
	 * @param id
	 */
	@RequestMapping(value="/{id}/profile", method=RequestMethod.DELETE)
	void deleteUsersProfilePicture(@PathVariable Long id) {
		//TODO
	}
}
