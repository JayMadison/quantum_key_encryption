package net.it691team1.qkdbackend.exceptions;


/**
 * Exception class to handle exceptions due to a bad filter pattern (null or 
 * characters other than /\-|
 * @author jaythomas
 *
 */
public class QKDFilterException extends Exception{
	/**
	 * Custom error class to pass the old output messages from 
	 * the Methods legacy class
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Error message text, intended to be extended by various contructors.
	 */
	String error;
	/**
	 * Default Constructor
	 */
	public QKDFilterException(){
		super();
		error = "There was a Problem With the Filter Set";
	}
	/**
	 * Constructor that takes a string as the error message 
	 * @param e
	 */
	public QKDFilterException(String e){
		this.error = e; 
	}
	/**
	 * Set Error Message
	 * @param s
	 */
	public void setError(String s){
		this.error = s; 
	}
	/**
	 * Returns the error message from the object
	 * @return error
	 */
	public String getError(){
	    return error;
	}
}