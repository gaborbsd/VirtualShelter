package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Handicap;

import java.util.List;

public interface HandicapFacade {
	
	Handicap findHandicapById(int handicapId);
	
	List<Handicap> findAll();
	
	void create(Handicap Handicap);
	
	void edit(Handicap Handicap);
	
	void deleteHandicapById(int handicapId);

}
