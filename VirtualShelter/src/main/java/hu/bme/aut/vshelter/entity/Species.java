package hu.bme.aut.vshelter.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "species", catalog = "vshelter", uniqueConstraints = {
        @UniqueConstraint(columnNames = "speciesName")})
public class Species {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "speciesName", unique = true, nullable = false)
    private String speciesName;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Breed> breeds;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public List<Breed> getBreeds() {
        return breeds;
    }

    public void setBreeds(List<Breed> breeds) {
        this.breeds = breeds;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Species other = (Species) obj;
        if (id != other.id)
            return false;
        return true;
    }

}
