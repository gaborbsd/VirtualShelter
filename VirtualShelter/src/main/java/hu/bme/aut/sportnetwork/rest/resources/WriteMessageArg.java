package hu.bme.aut.sportnetwork.rest.resources;

public class WriteMessageArg {
	
	private long conversationId;
	
	private String message;

	public long getConversationId() {
		return conversationId;
	}

	public void setConversationId(long conversationId) {
		this.conversationId = conversationId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
