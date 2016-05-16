package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Animal;

import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends AbstractRepository<Animal> {
	
}
