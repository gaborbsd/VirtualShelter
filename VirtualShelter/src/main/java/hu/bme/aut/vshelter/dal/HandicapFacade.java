package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Handicap;

import java.util.List;

public interface HandicapFacade {
	
	Handicap findHandicapById(long handicapId);
	
	List<Handicap> findAll();
	
	void create(Handicap handicap);
	
	void edit(Handicap handicap);
	
	void deleteHandicapById(long handicapId);

}
