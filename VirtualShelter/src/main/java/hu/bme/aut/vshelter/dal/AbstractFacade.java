package hu.bme.aut.vshelter.dal;

import java.util.List;

public interface AbstractFacade<T> {
	
	T findById(long id);
	
	List<T> findAll();
	
	void create(T entity);
	
	void edit(T entity);
	
	void deleteById(long Id);
}
