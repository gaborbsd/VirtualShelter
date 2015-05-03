package hu.bme.aut.vshelter.dal.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import hu.bme.aut.vshelter.dal.HandicapFacade;
import hu.bme.aut.vshelter.entity.Handicap;

public class HandicapFacadeInMemoryImpl implements HandicapFacade {
	
	private List<Handicap> handicaps = new ArrayList<Handicap>();

	@Override
	public Handicap findById(long handicapId) {
		try {
			return handicaps.stream()
					.filter(a -> a.getId() == handicapId).findFirst()
					.get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public List<Handicap> findAll() {
		return Collections.unmodifiableList(handicaps);
	}

	@Override
	public void create(Handicap handicap) {
		handicaps.add(handicap);
	}

	@Override
	public void edit(Handicap handicap) {
		handicaps
		.set(handicaps.indexOf(handicap), handicap);
	}

	@Override
	public void deleteById(long handicapId) {
		Handicap deleteHandicap = findById(handicapId);

		if (deleteHandicap != null)
			handicaps.remove(deleteHandicap);
		
	}

}
