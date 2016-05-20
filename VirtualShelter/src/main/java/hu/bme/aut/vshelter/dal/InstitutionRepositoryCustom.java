package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.entity.Institution;

import java.util.List;

public interface InstitutionRepositoryCustom extends AbstractRepositoryCustom<Institution> {

    public List<Institution> listInstituitionsOwnedByUser(long userId) throws VirtualShelterException;

    public List<Institution> listInstituitionsAdministeredByUser(long userId) throws VirtualShelterException;

    public Institution checkInstituitionAdministeredByUser(long id, long userId) throws VirtualShelterException;

}
