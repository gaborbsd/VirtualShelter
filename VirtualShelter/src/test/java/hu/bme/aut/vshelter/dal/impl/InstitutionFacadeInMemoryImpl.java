package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.dal.InstitutionFacade;
import hu.bme.aut.vshelter.entity.Institution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class InstitutionFacadeInMemoryImpl implements InstitutionFacade {

    private List<Institution> institutions = new ArrayList<Institution>();

    @Override
    public Institution findById(long institutionId) throws VirtualShelterException {
        try {
            return institutions.stream()
                    .filter(a -> a.getId() == institutionId).findFirst()
                    .get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public List<Institution> findAll() throws VirtualShelterException {
        return Collections.unmodifiableList(institutions);
    }

    @Override
    public void create(Institution institution) throws VirtualShelterException {
        institutions.add(institution);

    }

    @Override
    public void edit(Institution institution) throws VirtualShelterException {
        institutions
                .set(institutions.indexOf(institution), institution);
    }

    @Override
    public void deleteById(long institutionId) throws VirtualShelterException {
        Institution deleteInstitution = findById(institutionId);

        if (deleteInstitution != null)
            institutions.remove(deleteInstitution);
    }

    @Override
    public List<Institution> listInstituitionsOwnedByUser(long userId) throws VirtualShelterException {
        // TODO Auto-generated method stub
        return null;
    }
}
