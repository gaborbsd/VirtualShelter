package hu.bme.aut.vshelter.dal;

import org.springframework.stereotype.Repository;

import hu.bme.aut.vshelter.entity.Handicap;

@Repository
public interface HandicapRepository extends AbstractRepository<Handicap>, HandicapRepositoryCustom {

}
