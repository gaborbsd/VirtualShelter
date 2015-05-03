package hu.bme.aut.vshelter.api;

public class VirtualShelterException extends Exception {
	private Exception exception;
    
    public VirtualShelterException() {
		super();
	}

	public VirtualShelterException(String message)
	{
		super(message);
	}
	
	public VirtualShelterException(Exception e) {
		this.exception = e;
	}
	
	public Exception getSourceException() {
		return exception;
	}
}