package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Disease;

import java.util.List;

public interface DiseaseFacade {
	
	Disease findDiseaseById(long diseaseId);
	
	List<Disease> findAll();
	
	void create(Disease disease);
	
	void edit(Disease disease);
	
	void deleteDiseaseById(long diseaseId);

}
