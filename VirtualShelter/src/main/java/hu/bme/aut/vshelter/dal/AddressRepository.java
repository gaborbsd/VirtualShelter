package hu.bme.aut.vshelter.dal;

import org.springframework.stereotype.Repository;

import hu.bme.aut.vshelter.entity.Address;

@Repository
public interface AddressRepository extends AbstractRepository<Address>, AddressRepositoryCustom {

}
