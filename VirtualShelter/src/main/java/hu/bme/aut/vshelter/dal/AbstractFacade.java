package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.api.VirtualShelterException;

import java.util.List;

public interface AbstractFacade<T> {
	
	T findById(long id) throws VirtualShelterException;
	
	List<T> findAll() throws VirtualShelterException;
	
	void create(T entity) throws VirtualShelterException;
	
	void edit(T entity) throws VirtualShelterException;
	
	void deleteById(long Id) throws VirtualShelterException;
}
