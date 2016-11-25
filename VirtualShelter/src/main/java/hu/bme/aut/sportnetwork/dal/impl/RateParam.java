package hu.bme.aut.sportnetwork.dal.impl;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.sportnetwork.entity.User;

public class RateParam {

	private List<User> users;

	private List<Integer> rates;

	public List<User> getUsers() {
		if (users == null) {
			users = new ArrayList<>();
		}
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Integer> getRates() {
		if (rates == null) {
			rates = new ArrayList<>();
		}
		return rates;
	}

	public void setRates(List<Integer> rates) {
		this.rates = rates;
	}

}
