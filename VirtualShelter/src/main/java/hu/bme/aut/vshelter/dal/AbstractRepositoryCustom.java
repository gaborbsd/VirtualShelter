package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.api.VirtualShelterException;

public interface AbstractRepositoryCustom<T> {
	
	void edit(T entity) throws VirtualShelterException;

}
