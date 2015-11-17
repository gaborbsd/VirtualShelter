package hu.bme.aut.vshelter.dal;

import org.springframework.stereotype.Repository;

import hu.bme.aut.vshelter.entity.Breed;

@Repository
public interface BreedRepository extends AbstractRepository<Breed>, BreedRepositoryCustom {

}
