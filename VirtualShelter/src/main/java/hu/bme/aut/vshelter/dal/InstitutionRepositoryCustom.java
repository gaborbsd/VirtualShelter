package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.entity.Institution;

import java.util.List;

public interface InstitutionRepositoryCustom extends AbstractRepositoryCustom<Institution> {

    public List<Institution> listInstituitionsOwnedByUser(long userId) throws VirtualShelterException;

}
