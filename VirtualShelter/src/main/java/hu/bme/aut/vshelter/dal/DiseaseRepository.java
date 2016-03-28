package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Disease;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseRepository extends AbstractRepository<Disease>, DiseaseRepositoryCustom {

}
