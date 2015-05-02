package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Institution;

import java.util.List;

public interface InstitutionFacade {
	
	Institution findInstitutionById(long institutionId);
	
	List<Institution> findAll();
	
	void create(Institution institution);
	
	void edit(Institution institution);
	
	void deleteInstitutionById(long institutionId);
	
	public List<Institution> listInstituitionsOwnedByUser(long userId);

}
