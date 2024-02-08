package by.fksis.parking.model;

public class ParkingException extends Exception {
	
	private static final long serialVersionUID = 9150263854386078550L;

	public ParkingException() {
		
	}
	
	public ParkingException(String message) {
		super(message);
	}

	public ParkingException(Throwable cause) {
		super(cause);
	}
	
	public ParkingException(String message, Throwable cause) {
		super(message, cause);
	}
	
}