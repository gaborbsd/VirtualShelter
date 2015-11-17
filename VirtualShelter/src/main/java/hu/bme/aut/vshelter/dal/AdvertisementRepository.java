package hu.bme.aut.vshelter.dal;

import org.springframework.stereotype.Repository;

import hu.bme.aut.vshelter.entity.Advertisement;

@Repository
public interface AdvertisementRepository extends AbstractRepository<Advertisement>, AdvertisementRepositoryCustom {

}
