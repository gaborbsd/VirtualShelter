package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Address;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends AbstractRepository<Address>, AddressRepositoryCustom {

}
