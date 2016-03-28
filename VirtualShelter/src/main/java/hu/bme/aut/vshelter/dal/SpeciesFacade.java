package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.entity.Species;

public interface SpeciesFacade extends AbstractFacade<Species> {

    /**
     * Returns the speciesID from the owner of the given species name
     *
     * @return
     * @throws VirtualShelterException
     */
    public long getSpeciesIdfromSpeciesName(String speciesName) throws VirtualShelterException;

}
