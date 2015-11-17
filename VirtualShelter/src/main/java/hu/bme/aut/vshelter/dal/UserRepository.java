package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.entity.User;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends AbstractRepository<User>, UserRepositoryCustom {
	

}
