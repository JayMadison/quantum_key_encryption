package net.it691team1.qkdbackend.exceptions;

public class QKDatabaseException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String error;
	/**
	 * Default constructor for a generic error message.
	 */
	public QKDatabaseException() {
		error = "A Database Error has Occured";
	}
	/**
	 * Constructor for a custom error message. 
	 * @param er
	 */
	public QKDatabaseException(String er) {
		error = er;
	}
	/**
	 * Get the current error message
	 * @return String
	 */
	public String getError() {
		return error;
	}
	/**
	 * Sets the error message
	 * @param error
	 */
	public void setError(String error) {
		this.error = error;
	}
	
	
	

}
