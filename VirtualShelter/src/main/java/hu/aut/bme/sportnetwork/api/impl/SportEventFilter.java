package hu.aut.bme.sportnetwork.api.impl;

import hu.bme.aut.sportnetwork.entity.Sports;

public class SportEventFilter {

	private String text;
	
	private boolean title;
	
	private boolean city;
	
	private Sports sport;
	
	private boolean owner;
	
	private int levelFrom;
	
	private int levelTo;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean getCity() {
		return city;
	}

	public void setCity(boolean city) {
		this.city = city;
	}

	public Sports getSport() {
		return sport;
	}

	public void setSport(Sports sport) {
		this.sport = sport;
	}

	public boolean getOwner() {
		return owner;
	}

	public void setOwner(boolean owner) {
		this.owner = owner;
	}

	public int getLevelFrom() {
		return levelFrom;
	}

	public void setLevelFrom(int levelFrom) {
		this.levelFrom = levelFrom;
	}

	public int getLevelTo() {
		return levelTo;
	}

	public void setLevelTo(int levelTo) {
		this.levelTo = levelTo;
	}

	public boolean getTitle() {
		return title;
	}

	public void setTitle(boolean title) {
		this.title = title;
	}
	
	
}
