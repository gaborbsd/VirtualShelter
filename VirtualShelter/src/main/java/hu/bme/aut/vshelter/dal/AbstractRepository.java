package hu.bme.aut.vshelter.dal;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractRepository<T> extends CrudRepository<T, Long> {
	
	List<T> findAll();

}
