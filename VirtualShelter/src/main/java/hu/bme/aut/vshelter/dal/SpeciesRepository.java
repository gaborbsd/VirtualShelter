package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Species;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeciesRepository extends AbstractRepository<Species>, SpeciesRepositoryCustom {

}
