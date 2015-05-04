package hu.bme.aut.vshelter.dal.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.dal.UserFacade;
import hu.bme.aut.vshelter.entity.Breed;
import hu.bme.aut.vshelter.entity.User;

public class UserFacadeInMemoryImpl implements UserFacade {
	
	private List<User> users = new ArrayList<User>();

	@Override
	public User findById(long userId) {
		try {
			return users.stream()
					.filter(a -> a.getId() == userId).findFirst()
					.get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public List<User> findAll() {
		return Collections.unmodifiableList(users);
	}

	@Override
	public void create(User user) {
		users.add(user);
	}

	@Override
	public void edit(User user) {
		users
		.set(users.indexOf(user), user);
	}

	@Override
	public void deleteById(long userId) {
		User deleteUser = findById(userId);

		if (deleteUser != null)
			users.remove(deleteUser);
	}

	@Override
	public void promoteUserToSiteAdministrator(long userId) {
		
		String role = "site-administrator";
		User user = findById(userId);
		
		if( !user.getRoles().contains(role)) {
			user.getRoles().add(role);
		}
	}

	@Override
	public void revokeUserFromSiteAdministrator(long userId) {
		String role = "site-administrator";
		User user = findById(userId);
		
		if( user.getRoles().contains(role)) {
			user.getRoles().remove(role);
		}
	}

	@Override
	public long getUserIdfromEmail(String email) throws VirtualShelterException {
		try {
			return users.stream()
					.filter(a -> a.getEmail() == email).findFirst()
					.get().getId();
		} catch (NoSuchElementException e) {
			return 0;
		}
	}

}
