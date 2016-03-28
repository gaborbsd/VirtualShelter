package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Institution;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionRepository extends AbstractRepository<Institution>, InstitutionRepositoryCustom {

}
