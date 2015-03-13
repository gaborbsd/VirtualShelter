package hu.bme.aut.vshelter.api;

import hu.bme.aut.vshelter.entity.User;

import java.util.List;

public interface ISiteAdministrationOperations {
	
	public List<User> listAllUsers() throws VirtualShelterException;
	
	public void promoteSiteAdministrator(User user) throws VirtualShelterException;
	
	public void revokeSiteAdministrator(User user) throws VirtualShelterException;
	
	public void addSpecies(String species) throws VirtualShelterException;
	
	public void deleteSpecies(String species) throws VirtualShelterException;
	
	public void addBreed(String species) throws VirtualShelterException;
	
	public void deleteBreed(String species) throws VirtualShelterException;
}
