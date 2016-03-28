package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Advertisement;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementRepository extends AbstractRepository<Advertisement>, AdvertisementRepositoryCustom {

}
