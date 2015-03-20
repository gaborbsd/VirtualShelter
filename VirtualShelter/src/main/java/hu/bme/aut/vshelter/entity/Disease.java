package hu.bme.aut.vshelter.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Disease {
	@Id
	@GeneratedValue
	private int id;
	
	private String diceaseName;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getDiceaseName() {
		return diceaseName;
	}

	public void setDiceaseName(String diceaseName) {
		this.diceaseName = diceaseName;
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
		Disease other = (Disease) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
