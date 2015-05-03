package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Institution;

import java.util.List;

public interface InstitutionFacade extends AbstractFacade<Institution> {
		
	public List<Institution> listInstituitionsOwnedByUser(long userId);

}
