package hu.bme.aut.vshelter.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Species {
	@Id
	@GeneratedValue
	private int id;
	
	private String speciesName;
	
	private Set<Breed> breeds;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getSpeciesName() {
		return speciesName;
	}
	
	public void setSpeciesName(String speciesName) {
		this.speciesName = speciesName;
	}
	
	public Set<Breed> getBreeds() {
		return breeds;
	}
	
	public void setBreeds(Set<Breed> breeds) {
		this.breeds = breeds;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
