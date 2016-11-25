package hu.bme.aut.sportnetwork.rest.resources;

import java.util.ArrayList;
import java.util.List;

public class RateUsersArg {

	private long notificationId;

	private List<RateWrapper> rates;

	public long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(long notificationId) {
		this.notificationId = notificationId;
	}

	public List<RateWrapper> getRates() {
		if (rates == null) {
			return new ArrayList<>();
		}
		return rates;
	}

	public void setRates(List<RateWrapper> rates) {
		this.rates = rates;
	}

}
