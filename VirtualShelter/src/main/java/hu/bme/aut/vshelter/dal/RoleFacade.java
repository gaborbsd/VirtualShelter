package hu.bme.aut.vshelter.dal;

import hu.bme.aut.vshelter.entity.Role;

import java.util.List;

public interface RoleFacade {
	
	Role findRoleById(int roleId);
	
	List<Role> findAll();
	
	void create(Role role);
	
	void edit(Role role);
	
	void deleteRoleById(int roleId);

}
