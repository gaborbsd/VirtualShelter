package hu.bme.aut.sportnetwork.rest.resources;

public class FilterSportEventArg {
	
	private String text;
	
	private boolean title;
	
	private boolean city;
	
	private boolean sport;
	
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

	public boolean getSport() {
		return sport;
	}

	public void setSport(boolean sport) {
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
