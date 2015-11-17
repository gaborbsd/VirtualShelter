package hu.bme.aut.vshelter.dal;

import org.springframework.stereotype.Repository;

import hu.bme.aut.vshelter.entity.Institution;

@Repository
public interface InstitutionRepository extends AbstractRepository<Institution>, InstitutionRepositoryCustom {

}
