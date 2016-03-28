package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Breed;
import org.springframework.stereotype.Repository;

@Repository
public interface BreedRepository extends AbstractRepository<Breed>, BreedRepositoryCustom {

}
