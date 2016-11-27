package hu.bme.aut.sportnetwork.api;

public class SportNetworkException extends Exception {

	public SportNetworkException() {
		super();
	}

	public SportNetworkException(String message) {
		super(message);
	}

	public SportNetworkException(String message, Throwable cause) {
		super(message, cause);
	}

	public SportNetworkException(Throwable cause) {
		super(cause);
	}
}
