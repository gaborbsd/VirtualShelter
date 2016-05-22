package hu.bme.aut.sportnetwork.dal;

import org.springframework.stereotype.Repository;

import hu.bme.aut.sportnetwork.entity.User;

@Repository
public interface UserRepository extends AbstractRepository<User>, UserRepositoryCustom {

	User findByName(String name);
}
