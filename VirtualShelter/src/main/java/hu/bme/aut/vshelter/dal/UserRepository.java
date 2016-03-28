package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends AbstractRepository<User>, UserRepositoryCustom {


}
