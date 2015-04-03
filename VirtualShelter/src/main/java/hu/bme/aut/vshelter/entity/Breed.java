package hu.bme.aut.vshelter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "breed", catalog = "vshelter", uniqueConstraints = {
		@UniqueConstraint(columnNames = "breedName")})
public class Breed {
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	
	@Column(name = "breedName", unique = true, nullable = false)
	private String breedName;
	
	@ManyToOne
	private Species species;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getBreedName() {
		return breedName;
	}
	
	public void setBreedName(String breedName) {
		this.breedName = breedName;
	}
	
	public Species getSpecies() {
		return species;
	}
	
	public void setSpecies(Species species) {
		this.species = species;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int)id;
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
		Breed other = (Breed) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
