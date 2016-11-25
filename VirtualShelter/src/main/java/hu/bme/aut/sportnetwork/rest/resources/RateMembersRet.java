package hu.bme.aut.sportnetwork.rest.resources;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.sportnetwork.entity.User;

public class RateMembersRet {

	private List<UserLinkWrapper> users;

	public RateMembersRet() {
	}

	public RateMembersRet(List<User> usersTo) {
		users = WrapperUtils.toWrapperListCloseCurrent(usersTo);
	}

	public List<UserLinkWrapper> getUsers() {
		if (users == null) {
			users = new ArrayList<>();
		}
		return users;
	}

	public void setUsers(List<UserLinkWrapper> users) {
		this.users = users;
	}


}
