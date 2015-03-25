package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Institution;

import java.util.List;

public interface InstitutionFacade {
	
	Institution findInstitutionById(int institutionId);
	
	List<Institution> findAll();
	
	void create(Institution Institution);
	
	void edit(Institution Institution);
	
	void deleteInstitutionById(int institutionId);

}
