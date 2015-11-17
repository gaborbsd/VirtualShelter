package hu.bme.aut.vshelter.dal;

import org.springframework.stereotype.Repository;

import hu.bme.aut.vshelter.entity.Animal;

@Repository
public interface AnimalRepository extends AbstractRepository<Animal>, AnimalRepositoryCustom {

}
