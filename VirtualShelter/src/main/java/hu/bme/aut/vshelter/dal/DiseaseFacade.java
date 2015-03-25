package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Disease;

import java.util.List;

public interface DiseaseFacade {
	
	Disease findDiseaseById(int diseaseId);
	
	List<Disease> findAll();
	
	void create(Disease Disease);
	
	void edit(Disease Disease);
	
	void deleteDiseaseById(int diseaseId);

}
