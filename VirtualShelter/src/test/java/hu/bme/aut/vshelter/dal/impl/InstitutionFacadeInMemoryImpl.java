package hu.bme.aut.vshelter.dal.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import hu.bme.aut.vshelter.dal.InstitutionFacade;
import hu.bme.aut.vshelter.entity.Breed;
import hu.bme.aut.vshelter.entity.Institution;

public class InstitutionFacadeInMemoryImpl implements InstitutionFacade {

	private List<Institution> institutions = new ArrayList<Institution>();

	@Override
	public Institution findInstitutionById(long institutionId) {
		try {
			return institutions.stream()
					.filter(a -> a.getId() == institutionId).findFirst()
					.get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public List<Institution> findAll() {
		return Collections.unmodifiableList(institutions);
	}

	@Override
	public void create(Institution institution) {
		institutions.add(institution);
		
	}

	@Override
	public void edit(Institution institution) {
		institutions
		.set(institutions.indexOf(institution), institution);
	}

	@Override
	public void deleteInstitutionById(long institutionId) {
		Institution deleteInstitution = findInstitutionById(institutionId);

		if (deleteInstitution != null)
			institutions.remove(deleteInstitution);
	}
}
