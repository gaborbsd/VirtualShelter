package hu.bme.aut.vshelter.dal;

import org.springframework.stereotype.Repository;

import hu.bme.aut.vshelter.entity.Species;

@Repository
public interface SpeciesRepository extends AbstractRepository<Species>, SpeciesRepositoryCustom {

}
