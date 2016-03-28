package hu.bme.aut.vshelter.rest.resources;

import org.springframework.hateoas.ResourceSupport;

public class BreedResource extends ResourceSupport {
    private long breedId;
    private String name;

    public long getBreedId() {
        return breedId;
    }

    public void setBreedId(long breedId) {
        this.breedId = breedId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
