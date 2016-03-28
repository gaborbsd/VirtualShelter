package hu.bme.aut.vshelter.rest.resources;

import hu.bme.aut.vshelter.entity.Breed;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class SpeciesResource extends ResourceSupport {
    private long speciesId;
    private String name;
    private List<Breed> breeds;

    public long getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(long speciesId) {
        this.speciesId = speciesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Breed> getBreeds() {
        return breeds;
    }

    public void setBreeds(List<Breed> breeds) {
        this.breeds = breeds;
    }

}
