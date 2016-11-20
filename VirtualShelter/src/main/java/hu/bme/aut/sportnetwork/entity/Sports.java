package hu.bme.aut.sportnetwork.entity;

public enum Sports {
	FOOTBALL,
	BASKETBALL,
	TENNIS,
	VOLLEYBALL,
	HANDBALL,
	ICE_HOCKEY,
	BADMINTON,
	BASEBALL,
	AMERICAN_FOOTBALL,
	SNOOKER,
	TABLE_TENNIS,
	CHESS;
	
	public static Sports toSport(String text) {
		for (Sports s : Sports.values()) {
			if (s.toString().equals(text.toUpperCase())) {
				return s;
			}
		}
		return null;
	}
}
