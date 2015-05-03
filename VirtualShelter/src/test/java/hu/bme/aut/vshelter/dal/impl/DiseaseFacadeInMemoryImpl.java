package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.dal.DiseaseFacade;
import hu.bme.aut.vshelter.entity.Disease;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class DiseaseFacadeInMemoryImpl implements DiseaseFacade {

	private List<Disease> diseases = new ArrayList<Disease>();

	@Override
	public Disease findById(long diseaseId) {
		try {
			return diseases.stream()
					.filter(a -> a.getId() == diseaseId).findFirst()
					.get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public List<Disease> findAll() {
		return Collections.unmodifiableList(diseases);
	}

	@Override
	public void create(Disease disease) {
		diseases.add(disease);
	}

	@Override
	public void edit(Disease disease) {
		diseases
		.set(diseases.indexOf(disease), disease);
	}

	@Override
	public void deleteById(long diseaseId) {
		Disease deleteDisease = findById(diseaseId);

		if (deleteDisease != null)
			diseases.remove(deleteDisease);
	}
	
}
