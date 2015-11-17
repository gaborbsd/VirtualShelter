package hu.bme.aut.vshelter.dal;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.entity.User;

@NoRepositoryBean
public interface AbstractRepository<T> extends CrudRepository<T, Long> {
	
	T findById(long id) throws VirtualShelterException;
	
	List<T> findAll();
	

}
