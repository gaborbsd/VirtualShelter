package hu.bme.aut.vshelter.dal;

import org.springframework.stereotype.Repository;

import hu.bme.aut.vshelter.entity.Disease;

@Repository
public interface DiseaseRepository extends AbstractRepository<Disease>, DiseaseRepositoryCustom {

}
