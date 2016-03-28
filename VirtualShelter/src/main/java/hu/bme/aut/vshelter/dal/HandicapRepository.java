package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Handicap;
import org.springframework.stereotype.Repository;

@Repository
public interface HandicapRepository extends AbstractRepository<Handicap>, HandicapRepositoryCustom {

}
