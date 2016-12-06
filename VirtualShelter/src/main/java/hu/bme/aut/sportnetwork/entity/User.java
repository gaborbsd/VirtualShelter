package hu.bme.aut.sportnetwork.entity;

import java.util.ArrayList;
import java.util.List;

public class User {

	public long getId() {
		return 0;
	}

	public String getSalt() {
		return null;
	}

	public String getPassword() {
		return null;
	}

	public boolean getDeleted() {
		return false;
	}

	public List<String> getRoles() {
		return new ArrayList<>();
	}

}
