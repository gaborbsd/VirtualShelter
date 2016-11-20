package hu.bme.aut.sportnetwork.rest.resources;

public class WriteCommentArg {
	
	private long eventId;
	
	private String message;

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
