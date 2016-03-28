package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface AbstractRepository<T> extends CrudRepository<T, Long> {

    T findById(long id) throws VirtualShelterException;

    List<T> findAll();


}
