package hu.bme.aut.vshelter.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user", catalog = "vshelter")
public class User extends Advertiser {
	
	@Column(name = "password", unique = false, nullable = false)
	private String password;
	
	@Column(name = "introductionText", unique = false, nullable = true)
	private String introductionText;
	
	@ManyToMany
	private Set<Role> roles;
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getIntroductionText() {
		return introductionText;
	}
	
	public void setIntroductionText(String introductionText) {
		this.introductionText = introductionText;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
}